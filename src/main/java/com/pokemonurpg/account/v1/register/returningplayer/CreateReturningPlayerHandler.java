package com.pokemonurpg.account.v1.register.returningplayer;

import static com.pokemonurpg.account.v1.register.RegistrationConstants.CREATED_KNOWN_NAME_CLAIM_EXCEPTION_MESSAGE;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.register.CreatePlayerHandler;
import com.pokemonurpg.member.service.KnownNameClaimService;

@Service
public class CreateReturningPlayerHandler extends CreatePlayerHandler<CreateReturningPlayerDto> {

    @Resource
    private KnownNameClaimService knownNameClaimService;

    protected void handleUsernameDuplication(CreateReturningPlayerDto dto) {
        String username = dto.getInput().getName();
        String discordId = dto.getDiscordId();
        if (!isUsernameUnique(username)) {
            knownNameClaimService.create(discordId, username);
            throw new IllegalStateException(CREATED_KNOWN_NAME_CLAIM_EXCEPTION_MESSAGE);
        }
    }
}
