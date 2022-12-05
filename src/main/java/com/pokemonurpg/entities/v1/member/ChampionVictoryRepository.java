package com.pokemonurpg.entities.v1.member;

import com.pokemonurpg.entities.v1.gym.KnownChampion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionVictoryRepository  extends JpaRepository<ChampionVictory, ChampionVictoryKey> {
    ChampionVictory findByChallengerAndDefender(Member challenger, KnownChampion defender);
}
