package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.ChampionVictory;
import com.pokemonurpg.entities.v1.ChampionVictoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionVictoryRepository  extends JpaRepository<ChampionVictory, ChampionVictoryKey> {
    ChampionVictory findByChallengerAndDefender(Member challenger, Champion defender);
}
