package com.pokemonurpg.stats.repository;

import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.LegendaryProgress;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegendaryProgressRepository extends JpaRepository<LegendaryProgress, Integer> {
    List<LegendaryProgress> findByTrainer(Member trainer);
    LegendaryProgress findByTrainerAndIdLogUrlAndSection(Member member, String logUrl, Section section);
}