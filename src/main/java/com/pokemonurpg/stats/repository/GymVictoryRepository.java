package com.pokemonurpg.stats.repository;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.gym.models.KnownGymLeader;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.GymVictory;
import com.pokemonurpg.stats.models.GymVictoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymVictoryRepository extends JpaRepository<GymVictory, GymVictoryKey> {
    GymVictory findByChallengerAndDefenderAndGymAndLeague(Member challenger, KnownGymLeader defender, Gym gym, GymLeague league);
}
