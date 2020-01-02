package com.pokemonurpg.service.trainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonurpg.dto.security.*;
import com.pokemonurpg.object.trainer.*;
import com.pokemonurpg.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private OAuthService oAuthService;

    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})";
    private Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private Logger logger = LogManager.getLogger(MemberService.class);
    private ObjectMapper mapper = new ObjectMapper();

    Random rand = new Random();

    @Autowired
    public MemberService(MemberRepository memberRepository, RoleRepository roleRepository, MemberRoleService memberRoleService, MemberRoleRepository memberRoleRepository, PermissionRepository permissionRepository, RolePermissionRepository rolePermissionRepository, OAuthService oAuthService) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.memberRoleService = memberRoleService;
        this.memberRoleRepository = memberRoleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.oAuthService = oAuthService;
    }

    public List<Object> findAll() {
        return memberRepository.findAllNames();
    }

    public Member findByDiscordId(String id) { return memberRepository.findByDiscordId(id); }

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

    public boolean authorize(Member member, String... permissions) {
        List<MemberRole> roles = memberRoleRepository.findByIdMemberDbid(member.getDbid());

        for (String permission : permissions) {
            boolean found = false;
            for (MemberRole record : roles) {
                Role role = roleRepository.findByDbid(record.getId().getRoleDbid());
                if (role != null) {
                    List<RolePermission> rolePermissions = rolePermissionRepository.findByIdRoleDbid(role.getDbid());
                    for (RolePermission rolePermission : rolePermissions) {
                        Permission perm = permissionRepository.findByDbid(rolePermission.getId().getPermissionDbid());
                        if (perm != null && permission.equals(perm.getName())) {
                            found = true;
                        }
                    }
                }
            }
            if (!found) return false;
        }

        return true;
    }

    public boolean authenticateAndAuthorize(SessionDto session, String... permissions) {
        SessionDto currentSession = getCurrentUserSession(session);
        if (currentSession != null) {
            Member member = findByDiscordId(currentSession.getId());
            if (member != null) {
                if (!isBanned(member) && authorize(member, permissions)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Errors inviteUser(InviteUserDto input) {
        Errors errors = validateInviteUser(input);
        if (!errors.hasErrors()) {
            try {
                Member member = new Member();
                member.setUsername(input.getUsername());
                member.setDiscordId(input.getId());

                int salt = rand.nextInt(1000000000);
                member.setSalt(salt);

                memberRepository.save(member);
            } catch (Exception e) {
                logger.catching(e);
            }
        }
        return errors;
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

    public SessionDto login(String code) {
        try {
            if (code != null && !code.isEmpty() && !code.equals("")) {
                OAuthAccessTokenResponse accessTokenResponse = oAuthService.exchangeCodeForAccessToken(code);
                if (validateAccessTokenResponse(accessTokenResponse)) {
                    String id = oAuthService.getDiscordId(accessTokenResponse.getAccessToken());
                    if (id != null) {
                        Member member = memberRepository.findByDiscordId(id);
                        if (member != null) {
                            return startSecureSession(member, accessTokenResponse);
                        }
                        else throw new IllegalStateException("No user was found with Discord ID: " + id);
                    }
                    else throw new IllegalStateException("No ID was returned from the Discord request.");
                }
                else throw new IllegalStateException("No access token was returned from the Discord exchange.");
            }
            else return null;
        } catch (Exception e) {
            logger.catching(e);
            return null;
        }
    }

    public SessionDto refreshCurrentUserSession(SessionDto input) {
        try {
            SessionDto currentSession = getCurrentUserSession(input);
            if (currentSession != null) {
                OAuthAccessTokenResponse refreshedAccessTokenResponse = oAuthService.refreshAccessToken(input.getRefreshToken());
                if (validateAccessTokenResponse(refreshedAccessTokenResponse)) {
                    Member memberToAuthenticate = memberRepository.findByDiscordId(input.getId());
                    return startSecureSession(memberToAuthenticate, refreshedAccessTokenResponse);
                }
                return null;
            }
            return null;
        } catch (Exception e) {
            logger.catching(e);
            return null;
        }
    }

    public boolean isCurrentUser(SessionDto input, String name) {
        SessionDto session = getCurrentUserSession(input);
        if (name != null && session != null) {
            return name.equals(session.getUsername());
        }
        return false;
    }

    public SessionDto getCurrentUserSession(SessionDto input) {
        try {
            if (validateSessionDto(input)) {
                Member memberToAuthenticate = memberRepository.findByDiscordId(input.getId());
                if (hasCorrectAccessToken(memberToAuthenticate, input.getAccessToken())) {
                    String id = oAuthService.getDiscordId(input.getAccessToken());
                    if (input.getId().equals(id) && input.getUsername().equals(memberToAuthenticate.getUsername())) {
                        long expireTime = memberToAuthenticate.getSessionExpire();
                        if ((System.currentTimeMillis() / 1000) < expireTime - 60) {
                            return input;
                        }
                        return null;
                    }
                    return null;
                }
                return null;
            }
            else return null;
        } catch (Exception e) {
            logger.catching(e);
            return null;
        }
    }

    public SessionDto startSecureSession(Member member, OAuthAccessTokenResponse accessTokenResponse) throws NoSuchAlgorithmException {
        String accessToken = accessTokenResponse.getAccessToken();
        String refreshToken = accessTokenResponse.getRefreshToken();
        long expiresIn = Long.parseLong(accessTokenResponse.getExpiresIn());

        int salt = member.getSalt();
        member.setAccessToken(hash(accessToken + salt));
        member.setRefreshToken(hash(refreshToken + salt));
        member.setSessionExpire(expiresIn + (System.currentTimeMillis() / 1000));

        memberRepository.save(member);

        return new SessionDto(member.getUsername(), member.getDiscordId(), accessToken, refreshToken);
    }

    public boolean isBanned(Member member) {
        List<MemberRole> roles = memberRoleRepository.findByIdMemberDbid(member.getDbid());
        for (MemberRole record : roles) {
            Role role = roleRepository.findByDbid(record.getId().getRoleDbid());
            if (role.getName().equals("Banned")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCorrectAccessToken(Member member, String accessTokenToVerify) {
        try {
            String accessToken = member.getAccessToken();
            int salt = member.getSalt();
            String loginCheck = hash(accessTokenToVerify + salt);
            if (loginCheck != null && !loginCheck.equals("") && !loginCheck.isEmpty()) {
                if (loginCheck.equals(accessToken)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateAccessTokenResponse(OAuthAccessTokenResponse input) {
        if (input != null) {
            if (input.getAccessToken() == null || input.getAccessToken().isEmpty() || input.getAccessToken().equals("")) {
                return false;
            }
            if (input.getRefreshToken() == null || input.getRefreshToken().isEmpty() || input.getRefreshToken().equals("")) {
                return false;
            }
            if (input.getExpiresIn() == null || input.getExpiresIn().isEmpty() || input.getExpiresIn().equals("")) {
                return false;
            }
            return true;
        }
        else return false;
    }

    public Errors validateInviteUser(InviteUserDto input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        if (input != null) {
            String username = input.getUsername();
            if (username == null || username.isEmpty() || username.equals("")) {
                errors.rejectValue("username", "Provided username was empty.");
            }
            else if (username.length() < 3 || username.length() > 30) {
                errors.rejectValue("username", "Username must be between 3 and 30 characters long.");
            }
            else {
                Member existingMember = memberRepository.findByUsername(username);
                if (existingMember != null) {
                    errors.rejectValue("username", "Provided username was not unique!");
                }
            }

            String id = input.getId();
            if (id == null || id.isEmpty() || id.equals("")) {
                errors.rejectValue("id", "Provided ID was empty.");
            }
            else if (id.length() > 20) {
                errors.rejectValue("id", "Provided ID had length greater than 20. Please contact the system administrator.");
            }
            else {
                Member existingMember = memberRepository.findByDiscordId(id);
                if (existingMember != null) {
                    errors.rejectValue("id", "Can't invite a user that is already registered!");
                }
            }
        }
        else {
            errors.reject("No invite details were provided.");
        }

        return errors;
    }

    public boolean validateSessionDto(SessionDto input) {
        if (input != null) {
            if (input.getUsername() == null || input.getUsername().isEmpty() || input.getUsername().equals("")) {
                return false;
            }
            if (input.getAccessToken() == null || input.getAccessToken().isEmpty() || input.getAccessToken().equals("")) {
                return false;
            }
            if (input.getRefreshToken() == null || input.getRefreshToken().isEmpty() || input.getRefreshToken().equals("")) {
                return false;
            }
            if (input.getId() == null || input.getId().isEmpty() || input.getId().equals("")) {
                return false;
            }
            return true;
        }
        else return false;
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
