package com.pokemonurpg.stats.repository;

import com.pokemonurpg.configuration.v1.member.member.model.LegendaryProgress;
import com.pokemonurpg.configuration.v1.site.section.model.Section;
import com.pokemonurpg.member.models.Member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegendaryProgressRepository extends JpaRepository<LegendaryProgress, Integer> {
    List<LegendaryProgress> findByTrainer(Member trainer);
    LegendaryProgress findByTrainerAndIdLogUrlAndSection(Member member, String logUrl, Section section);
}