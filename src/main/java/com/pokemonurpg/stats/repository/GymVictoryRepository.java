package com.pokemonurpg.stats.repository;

import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.configuration.v1.member.member.model.GymVictory;
import com.pokemonurpg.configuration.v1.member.member.model.GymVictoryKey;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.model.KnownGymLeader;
import com.pokemonurpg.configuration.v1.member.member.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GymVictoryRepository extends JpaRepository<GymVictory, GymVictoryKey> {
    GymVictory findByChallengerAndDefenderAndGymAndLeague(Member challenger, KnownGymLeader defender, Gym gym, League league);
}
