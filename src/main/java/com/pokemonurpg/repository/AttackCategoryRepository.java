package com.pokemonurpg.repository;

import com.pokemonurpg.object.AttackCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttackCategoryRepository extends JpaRepository<AttackCategory, Integer> {
    AttackCategory findByName(String name);
}
