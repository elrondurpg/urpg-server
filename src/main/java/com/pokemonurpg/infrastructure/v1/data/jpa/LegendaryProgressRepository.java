package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Section;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.LegendaryProgress;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegendaryProgressRepository extends JpaRepository<LegendaryProgress, Integer> {
    List<LegendaryProgress> findByTrainer(Member trainer);
    LegendaryProgress findByTrainerAndIdLogUrlAndSection(Member member, String logUrl, Section section);
}