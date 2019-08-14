package com.pokemonurpg.service;

import com.pokemonurpg.Authenticated;
import com.pokemonurpg.dto.InputDto;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.repository.MemberRepository;
import com.pokemonurpg.repository.MemberRoleRepository;
import com.pokemonurpg.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class MemberService
{
    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private MemberRoleRepository memberRoleRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, RoleRepository roleRepository, MemberRoleRepository memberRoleRepository) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.memberRoleRepository = memberRoleRepository;
    }

    public boolean authenticate(Authenticated authDetails) {
        if (hasRequiredFields(authDetails)) {
            Member member = memberRepository.findByUsername(authDetails.getUsername());
            if (hasCorrectPassword(authDetails, member)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasRequiredFields(Authenticated authDetails) {
        try {
            if (authDetails.getId() == null || authDetails.getId().equals("") || authDetails.getId().isEmpty())
                return false;
            if (authDetails.getUsername() == null || authDetails.getUsername().equals("") || authDetails.getUsername().isEmpty())
                return false;
            if (authDetails.getLoginString() == null || authDetails.getLoginString().equals("") || authDetails.getLoginString().isEmpty())
                return false;
            if (authDetails.getBrowser() == null || authDetails.getBrowser().equals("") || authDetails.getBrowser().isEmpty())
                return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasCorrectPassword(Authenticated authDetails, Member member) {
        try {
            String password = member.getPassword();

            String preHash = "" + password + authDetails.getBrowser();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(preHash.getBytes());

            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            String loginCheck = sb.toString();
            if (loginCheck.equals(authDetails.getLoginString()))
            {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
