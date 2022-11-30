package com.pokemonurpg.stats.repository;

import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.model.KnownEliteFourMember;
import com.pokemonurpg.configuration.v1.member.member.model.EliteFourVictory;
import com.pokemonurpg.configuration.v1.member.member.model.EliteFourVictoryKey;
import com.pokemonurpg.configuration.v1.member.member.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EliteFourVictoryRepository extends JpaRepository<EliteFourVictory, EliteFourVictoryKey> {
    EliteFourVictory findByChallengerAndDefender(Member challenger, KnownEliteFourMember defender);
}
