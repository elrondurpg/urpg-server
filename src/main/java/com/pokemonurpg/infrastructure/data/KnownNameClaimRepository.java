package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.KnownNameClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnownNameClaimRepository extends JpaRepository<KnownNameClaim, Integer> {
    KnownNameClaim findByDiscordId(String discordId);
    KnownNameClaim findByName(String name);
}
