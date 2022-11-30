package com.pokemonurpg.account.v1.register.veteran.internal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.member.models.KnownNameClaim;
import com.pokemonurpg.member.repository.KnownNameClaimRepository;

@Service
public class ClaimKnownNameHandler {

    @Resource
    private KnownNameClaimRepository repository;

    public ClaimKnownNameResponse handle(ClaimKnownNameRequest request) {
        KnownNameClaim model = createModel(request);
        repository.save(model);
        return createResponse(model);
    }

    private KnownNameClaim createModel(ClaimKnownNameRequest request) {
        return KnownNameClaim.builder()
            .discordId(request.getDiscordId())
            .name(request.getName())
            .build();
    }

    private ClaimKnownNameResponse createResponse(KnownNameClaim model) {
        return ClaimKnownNameResponse.builder()
            .discordId(model.getDiscordId())
            .name(model.getName())
            .build();
    }
}
