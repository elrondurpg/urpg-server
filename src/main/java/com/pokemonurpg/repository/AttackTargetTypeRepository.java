package com.pokemonurpg.repository;

import com.pokemonurpg.object.AttackTargetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttackTargetTypeRepository extends JpaRepository<AttackTargetType, Integer> {
    AttackTargetType findByName(String name);
}
