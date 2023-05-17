package com.pokemonurpg.registration.v1.members;

import com.pokemonurpg.infrastructure.data.KnownNameClaimRepository;
import com.pokemonurpg.entities.KnownNameClaim;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class KnownNameClaimService {

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
