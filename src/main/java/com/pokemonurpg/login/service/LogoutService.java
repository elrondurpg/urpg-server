package com.pokemonurpg.login.service;

import com.pokemonurpg.entities.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
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
