package com.pokemonurpg.stats.repository;

import com.pokemonurpg.configuration.v1.gym.knownchampion.model.KnownChampion;
import com.pokemonurpg.configuration.v1.member.member.model.ChampionVictory;
import com.pokemonurpg.configuration.v1.member.member.model.ChampionVictoryKey;
import com.pokemonurpg.configuration.v1.member.member.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionVictoryRepository  extends JpaRepository<ChampionVictory, ChampionVictoryKey> {
    ChampionVictory findByChallengerAndDefender(Member challenger, KnownChampion defender);
}
