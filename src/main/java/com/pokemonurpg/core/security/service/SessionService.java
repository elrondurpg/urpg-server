package com.pokemonurpg.core.security.service;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.member.models.Member;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public SessionDto create(Member member, OAuthAccessTokenResponse accessTokenResponse) {
        return new SessionDto(member.getUsername(), member.getDiscordId(), accessTokenResponse.getAccessToken());
    }

}
