package com.pokemonurpg.account.v1.register;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.lib.security.v1.Session;
import com.pokemonurpg.security.service.OAuthService;

import javax.annotation.Resource;

public abstract class RegisterPlayerHandler<Dto extends RegisterPlayerDto, Handler extends CreatePlayerHandler<? extends CreatePlayerDto<Dto>>> {
    @Resource
    protected OAuthService oAuthService;

    @Resource
    protected Handler handler;

    public Session handle(Dto input) {
        try {
            return createSessionForRegistrationIfSuccessful(input);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    protected abstract Session createSessionForRegistrationIfSuccessful(Dto input);

    protected Session createSession(Member member, String accessToken) {
        return new Session(member.getName(), member.getDiscordId(), accessToken);
    }
}
