package com.pokemonurpg.entities.v1.member;

import com.pokemonurpg.entities.v1.gym.Gym;
import com.pokemonurpg.entities.v1.gym.League;
import com.pokemonurpg.entities.v1.gym.KnownGymLeader;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GymVictoryRepository extends JpaRepository<GymVictory, GymVictoryKey> {
    GymVictory findByChallengerAndDefenderAndGymAndLeague(Member challenger, KnownGymLeader defender, Gym gym, League league);
}
