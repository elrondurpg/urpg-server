package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.KnownEliteFourMember;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.EliteFourVictory;
import com.pokemonurpg.entities.v1.EliteFourVictoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EliteFourVictoryRepository extends JpaRepository<EliteFourVictory, EliteFourVictoryKey> {
    EliteFourVictory findByChallengerAndDefender(Member challenger, KnownEliteFourMember defender);
}
