package com.pokemonurpg.member.repository;

import com.pokemonurpg.member.models.KnownNameClaim;
import com.pokemonurpg.member.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KnownNameClaimRepository extends JpaRepository<KnownNameClaim, Integer> {
    KnownNameClaim findByDiscordId(String discordId);
    KnownNameClaim findByName(String name);
}
