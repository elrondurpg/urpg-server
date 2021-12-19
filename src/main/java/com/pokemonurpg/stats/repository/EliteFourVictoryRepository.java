package com.pokemonurpg.stats.repository;

import com.pokemonurpg.gym.models.KnownEliteFourMember;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.EliteFourVictory;
import com.pokemonurpg.stats.models.EliteFourVictoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EliteFourVictoryRepository extends JpaRepository<EliteFourVictory, EliteFourVictoryKey> {
    EliteFourVictory findByChallengerAndDefender(Member challenger, KnownEliteFourMember defender);
}
