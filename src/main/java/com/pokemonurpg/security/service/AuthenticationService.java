package com.pokemonurpg.security.service;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthenticationService {

    @Resource
    private MemberService memberService;

    @Resource
    private BotAuthenticationService botAuthenticationService;

    @Resource
    private HumanAuthenticationService humanAuthenticationService;

    public Member authenticate(String discordId, String accessToken) {
        Member matchedMember = memberService.findByDiscordId(discordId);
        if (matchedMember != null) {
            if (matchedMember.isBot()) {
                return botAuthenticationService.authenticate(matchedMember, accessToken);
            }
            else return humanAuthenticationService.authenticate(matchedMember, accessToken);
        }
        else return null;
    }
}
