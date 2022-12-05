package com.pokemonurpg.entities.v1.member;

import com.pokemonurpg.entities.v1.gym.KnownEliteFourMember;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EliteFourVictoryRepository extends JpaRepository<EliteFourVictory, EliteFourVictoryKey> {
    EliteFourVictory findByChallengerAndDefender(Member challenger, KnownEliteFourMember defender);
}
