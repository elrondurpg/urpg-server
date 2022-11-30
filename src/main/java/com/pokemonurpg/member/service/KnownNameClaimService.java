package com.pokemonurpg.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.member.models.KnownNameClaim;
import com.pokemonurpg.member.repository.KnownNameClaimRepository;

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
