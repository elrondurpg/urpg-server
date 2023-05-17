package com.pokemonurpg.stats.repository;

import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymLeague;
import com.pokemonurpg.entities.KnownGymLeader;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.models.GymVictory;
import com.pokemonurpg.stats.models.GymVictoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymVictoryRepository extends JpaRepository<GymVictory, GymVictoryKey> {
    GymVictory findByChallengerAndDefenderAndGymAndLeague(Member challenger, KnownGymLeader defender, Gym gym, GymLeague league);
}
