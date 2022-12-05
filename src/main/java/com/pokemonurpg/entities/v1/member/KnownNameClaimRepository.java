package com.pokemonurpg.entities.v1.member;

import com.pokemonurpg.entities.v1.shared.NamedRepository;

public interface KnownNameClaimRepository extends NamedRepository<KnownNameClaim> {
    KnownNameClaim findByDiscordId(String discordId);
}
