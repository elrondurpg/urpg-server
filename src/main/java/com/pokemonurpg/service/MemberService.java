package com.pokemonurpg.service;

import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.dto.security.LoginDto;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class MemberService
{
    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private MemberRoleRepository memberRoleRepository;
    private PermissionRepository permissionRepository;
    private RolePermissionRepository rolePermissionRepository;

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
        if (hasRequiredFields(login)) {
            Member member = memberRepository.findByUsername(login.getUsername());
            if (hasCorrectPassword(login, member)) {
                return createAuthToken(login);
            }
        }
        return null;
    }

    public Member authenticate(Authenticated authDetails) {
        if (hasRequiredFields(authDetails)) {
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

    public boolean hasRequiredFields(LoginDto login) {
        try {
            if (login.getUsername() == null || login.getUsername().equals("") || login.getUsername().isEmpty())
                return false;
            if (login.getPassword() == null || login.getPassword().equals("") || login.getPassword().isEmpty())
                return false;
            if (login.getBrowser() == null || login.getBrowser().equals("") || login.getBrowser().isEmpty())
                return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasRequiredFields(Authenticated authDetails) {
        try {
            if (authDetails.getUsername() == null || authDetails.getUsername().equals("") || authDetails.getUsername().isEmpty())
                return false;
            if (authDetails.getAuthToken() == null || authDetails.getAuthToken().equals("") || authDetails.getAuthToken().isEmpty())
                return false;
            if (authDetails.getBrowser() == null || authDetails.getBrowser().equals("") || authDetails.getBrowser().isEmpty())
                return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasCorrectPassword(LoginDto login, Member member) {
        try {
            String password = login.getPassword();
            int dbid = member.getDbid();

            String loginCheck = hash("" + password + dbid);
            if (loginCheck != null && !loginCheck.equals("") && !loginCheck.isEmpty()) {
                String memberPassword = member.getPassword();
                if (memberPassword != null && !memberPassword.equals("") && !memberPassword.isEmpty()) {
                    if (loginCheck.equals(member.getPassword())) {
                        return true;
                    }
                }
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public String createAuthToken(LoginDto login) {
        try {
            return hash("" + login.getPassword() + login.getBrowser());
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
