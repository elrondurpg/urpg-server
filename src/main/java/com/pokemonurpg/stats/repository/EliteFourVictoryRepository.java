package com.pokemonurpg.stats.repository;

import com.pokemonurpg.entities.KnownEliteFourMember;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.models.EliteFourVictory;
import com.pokemonurpg.stats.models.EliteFourVictoryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EliteFourVictoryRepository extends JpaRepository<EliteFourVictory, EliteFourVictoryKey> {
    EliteFourVictory findByChallengerAndDefender(Member challenger, KnownEliteFourMember defender);
}
