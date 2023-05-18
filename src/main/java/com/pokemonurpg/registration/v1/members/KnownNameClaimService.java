package com.pokemonurpg.registration.v1.members;

import com.pokemonurpg.infrastructure.v1.data.jpa.KnownNameClaimRepository;
import com.pokemonurpg.entities.v1.KnownNameClaim;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class KnownNameClaimService {
    // TODO REMOVE THIS COMMENT
    @Resource
    private KnownNameClaimRepository knownNameClaimRepository;

    public KnownNameClaim findByDiscordId(String discordId) {
        return knownNameClaimRepository.findByDiscordId(discordId);
    }

    public KnownNameClaim findByName(String name) {
        return knownNameClaimRepository.findByName(name);
    }

    public void create(String discordId, String name) {
        KnownNameClaim claim = new KnownNameClaim(discordId, name);
        knownNameClaimRepository.save(claim);
    }
}
