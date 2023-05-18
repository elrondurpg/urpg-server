package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.FeatureFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeatureFlagRepository extends JpaRepository<FeatureFlag, Integer> {
	@Query("select t.name from Flag t")
    List<String> findAllNames();
    FeatureFlag findByDbid(int dbid);
    FeatureFlag findByName(String name);
    FeatureFlag findFirstByNameStartingWith(String name);
}
