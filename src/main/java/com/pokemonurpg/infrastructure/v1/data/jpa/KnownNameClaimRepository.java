package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.KnownNameClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnownNameClaimRepository extends JpaRepository<KnownNameClaim, Integer> {
    KnownNameClaim findByDiscordId(String discordId);
    KnownNameClaim findByName(String name);
}
