package com.pokemonurpg.stats.repository;

import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.LegendaryProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegendaryProgressRepository extends JpaRepository<LegendaryProgress, Integer> {
    LegendaryProgress findByTrainerAndIdLogUrlAndSection(Member member, String logUrl, Section section);
}