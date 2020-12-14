package com.pokemonurpg.stats.repository;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.EarnedRibbon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarnedRibbonRepository extends JpaRepository<EarnedRibbon, Integer> {
    EarnedRibbon findByDbid(Integer dbid);
}