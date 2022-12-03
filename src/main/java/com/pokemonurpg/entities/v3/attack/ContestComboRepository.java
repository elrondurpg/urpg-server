package com.pokemonurpg.entities.v3.attack;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.configuration.v1.attack.attack.model.ContestCombo;
import com.pokemonurpg.entities.v3.contest.ContestTypeEntity;

public interface ContestComboRepository extends JpaRepository<ContestComboEntity, ContestComboKeyEntity> {
    ContestCombo findByFirstAttackAndSecondAttackAndGeneration(AttackEntity firstAttack, AttackEntity secondAttack, ContestTypeEntity generation);
}
