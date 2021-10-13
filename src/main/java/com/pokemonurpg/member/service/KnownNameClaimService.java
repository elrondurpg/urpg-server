package com.pokemonurpg.member.service;

import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.member.input.PermissionInputDto;
import com.pokemonurpg.member.models.KnownNameClaim;
import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.repository.KnownNameClaimRepository;
import com.pokemonurpg.member.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
