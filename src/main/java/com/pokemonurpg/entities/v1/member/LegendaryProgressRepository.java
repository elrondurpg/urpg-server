package com.pokemonurpg.entities.v1.member;

import com.pokemonurpg.entities.v1.site.Section;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegendaryProgressRepository extends JpaRepository<LegendaryProgress, Integer> {
    List<LegendaryProgress> findByTrainer(Member trainer);
    LegendaryProgress findByTrainerAndIdLogUrlAndSection(Member member, String logUrl, Section section);
}