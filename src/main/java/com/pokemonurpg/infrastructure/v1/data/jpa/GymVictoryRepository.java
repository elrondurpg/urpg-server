package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeague;
import com.pokemonurpg.entities.v1.KnownGymLeader;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.GymVictory;
import com.pokemonurpg.entities.v1.GymVictoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymVictoryRepository extends JpaRepository<GymVictory, GymVictoryKey> {
    GymVictory findByChallengerAndDefenderAndGymAndLeague(Member challenger, KnownGymLeader defender, Gym gym, GymLeague league);
}
