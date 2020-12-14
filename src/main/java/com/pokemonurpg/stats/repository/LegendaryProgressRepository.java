package com.pokemonurpg.stats.repository;

import com.pokemonurpg.stats.models.LegendaryProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegendaryProgressRepository extends JpaRepository<LegendaryProgress, Integer> {
    LegendaryProgress findByDbid(Integer dbid);
}