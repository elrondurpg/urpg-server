package com.pokemonurpg.security.service;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Provider;

@Service
public class LogoutService {

    @Resource
    private Provider<SessionService> sessionServiceProvider;

    @Resource
    private MemberService memberService;

    public void logout() {
        SessionService sessionService = sessionServiceProvider.get();
        Member member = sessionService.getAuthenticatedMember();
        if (member != null) {
            memberService.logout(member);
        }
    }

}