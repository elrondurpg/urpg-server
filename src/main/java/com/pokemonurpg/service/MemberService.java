package com.pokemonurpg.service;

import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.dto.security.LoginDto;
import com.pokemonurpg.dto.security.RegisterBetaDto;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberService
{
    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private MemberRoleRepository memberRoleRepository;
    private PermissionRepository permissionRepository;
    private RolePermissionRepository rolePermissionRepository;

    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})";
    private Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    Random rand = new Random();

    @Autowired
    public MemberService(MemberRepository memberRepository, RoleRepository roleRepository, MemberRoleRepository memberRoleRepository,
                         PermissionRepository permissionRepository, RolePermissionRepository rolePermissionRepository) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.memberRoleRepository = memberRoleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
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
}
