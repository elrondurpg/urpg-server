package com.pokemonurpg.stats.repository;

import com.pokemonurpg.entities.KnownChampion;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.models.ChampionVictory;
import com.pokemonurpg.stats.models.ChampionVictoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionVictoryRepository  extends JpaRepository<ChampionVictory, ChampionVictoryKey> {
    ChampionVictory findByChallengerAndDefender(Member challenger, KnownChampion defender);
}
