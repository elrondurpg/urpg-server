package com.pokemonurpg.service;

import com.pokemonurpg.dto.security.*;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberService
{
    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private MemberRoleService memberRoleService;
    private MemberRoleRepository memberRoleRepository;
    private PermissionRepository permissionRepository;
    private RolePermissionRepository rolePermissionRepository;

    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})";
    private Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    Random rand = new Random();

    @Autowired
    public MemberService(MemberRepository memberRepository, RoleRepository roleRepository, MemberRoleService memberRoleService, MemberRoleRepository memberRoleRepository, PermissionRepository permissionRepository, RolePermissionRepository rolePermissionRepository) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.memberRoleService = memberRoleService;
        this.memberRoleRepository = memberRoleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    public List<Object> findAll() {
        return memberRepository.findAllNames();
    }

    public MemberDto findByName(String name) {
        Member member = memberRepository.findByUsername(name);
        MemberDto dto = null;
        if (member != null) {
            dto = new MemberDto(member);
        }
        else {
            List<Member> results = memberRepository.findByUsernameStartingWith(name);
            if (!results.isEmpty()) {
                member = results.get(0);
                dto = new MemberDto(member);
            }
            else return null;
        }

        dto.setRoles(buildDtoRoles(member.getDbid()));

        return dto;
    }

    public List<String> buildDtoRoles(int memberDbid) {
        List<MemberRole> memberRoles = memberRoleRepository.findByIdMemberDbid(memberDbid);
        List<String> dtoRoles = new ArrayList<>();
        for (MemberRole memberRole : memberRoles) {
            int dbid = memberRole.getId().getRoleDbid();
            Role role = roleRepository.findByDbid(dbid);
            if (role != null) {
                dtoRoles.add(role.getName());
            }
        }
        return dtoRoles;
    }

    public String login(LoginDto login) {
        if (exists(login.getBrowser()) && exists(login.getPassword()) && exists(login.getUsername())) {
            Member member = memberRepository.findByUsername(login.getUsername());
            if (hasCorrectPassword(login, member)) {
                return createAuthToken(login, member);
            }
        }
        return null;
    }

    public boolean registerBeta(RegisterBetaDto registerBetaDto) {
        try {
            if (validateRegistrationBeta(registerBetaDto)) {
                Member member = memberRepository.findByUsername(registerBetaDto.getUsername());
                if (hasCorrectBetaKey(registerBetaDto, member)) {
                    int salt = rand.nextInt(1000000000);
                    member.setSalt(salt);
                    member.setPassword(hash(registerBetaDto.getPassword() + salt));
                    member.setBetaKey(null);
                    memberRepository.save(member);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Member authenticate(Authenticated authDetails) {
        if (exists(authDetails.getAuthToken()) && exists(authDetails.getBrowser()) && exists(authDetails.getUsername())) {
            Member member = memberRepository.findByUsername(authDetails.getUsername());
            if (hasCorrectAuthToken(authDetails, member)) {
                return member;
            }
        }

        return null;
    }

    public boolean authorize(Member member, String permission) {
        List<MemberRole> roles = memberRoleRepository.findByIdMemberDbid(member.getDbid());

        for (MemberRole record : roles) {
            Role role = roleRepository.findByDbid(record.getId().getRoleDbid());
            if (role != null) {
                List<RolePermission> rolePermissions = rolePermissionRepository.findByIdRoleDbid(role.getDbid());
                for (RolePermission rolePermission : rolePermissions) {
                    Permission perm = permissionRepository.findByDbid(rolePermission.getId().getPermissionDbid());
                    if (perm != null && permission.equals(perm.getName())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String inviteUser(String username) {
        if (validateInviteUser(username)) {
            try {
                Member member = new Member();
                member.setUsername(username);
                member.setEmail("");
                member.setPassword("");

                String betaKey = RandomStringUtils.randomAlphanumeric(10);
                member.setBetaKey(hash(betaKey));
                memberRepository.save(member);

                return betaKey;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else return null;
    }

    public boolean validateInviteUser(String username) {
        if (username == null || username.isEmpty() || username.equals("")) {
            return false;
        }
        else return true;
    }

    public boolean hasCorrectPassword(LoginDto login, Member member) {
        try {
            String password = login.getPassword();
            int salt = member.getSalt();

            String loginCheck = hash(password + salt);
            if (loginCheck != null && !loginCheck.equals("") && !loginCheck.isEmpty()) {
                String memberPassword = member.getPassword();
                if (memberPassword != null && !memberPassword.equals("") && !memberPassword.isEmpty()) {
                    if (loginCheck.equals(memberPassword)) {
                        return true;
                    }
                }
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public String createAuthToken(LoginDto login, Member member) {
        try {
            return hash("" + member.getPassword() + login.getBrowser());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean hasCorrectAuthToken(Authenticated authDetails, Member member) {
        try {
            String password = member.getPassword();

            String loginCheck = hash("" + password + authDetails.getBrowser());
            if (loginCheck != null && !loginCheck.equals("") && !loginCheck.isEmpty()) {
                if (loginCheck.equals(authDetails.getAuthToken())) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateRegistrationBeta(RegisterBetaDto registerBetaDto) {
        try {
            if (exists(registerBetaDto.getUsername()) && exists(registerBetaDto.getBetaKey()) && exists(registerBetaDto.getPassword())) {
                String password = registerBetaDto.getPassword();
                matcher = pattern.matcher(password);
                return matcher.matches();
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasCorrectBetaKey(RegisterBetaDto registerBetaDto, Member member) {
        try {
            String betaKey = member.getBetaKey();

            String keyCheck = hash(registerBetaDto.getBetaKey());
            if (exists(betaKey) && exists(keyCheck)) {
                if (betaKey.equals(keyCheck)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean exists(String s) {
        if (s == null || s.equals("") || s.isEmpty()) {
            return false;
        }
        else return true;
    }

    public String hash(String cleartext) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(cleartext.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public Errors updateMember(MemberInputDto input) {
        Errors errors = validateMemberUpdate(input);
        if (!errors.hasErrors()) {
            Member existingMember = memberRepository.findByUsername(input.getName());
            if (input.getName() != null) {
                existingMember.setUsername(input.getName());
            }

            memberRepository.save(existingMember);
            int dbid = existingMember.getDbid();

            memberRoleService.updateAll(dbid, input.getRoles());
        }
        return errors;
    }

    public Errors validateMemberUpdate(MemberInputDto input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Member existingRecord = memberRepository.findByUsername(input.getName());
        if (existingRecord != null) {
            if (input.getName() != null && (input.getName().length() < 3 || input.getName().length() > 17)) {
                errors.reject("Member name " + input.getName() + " is invalid.");
            }

            if (input.getRoles() != null) {
                for (MemberRoleInputDto memberRoleInputDto : input.getRoles()) {
                    if (memberRoleInputDto.getName() != null && roleRepository.findByName(memberRoleInputDto.getName()) == null) {
                        errors.reject("Role name " + memberRoleInputDto.getName() + " is invalid.");
                    }
                }
            }
        }
        else {
            errors.reject("Member " + input.getName() + " doesn't exist.");
        }

        return errors;
    }
}
