package com.pokemonurpg.entities.v1.attack;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.v1.contest.ContestType;

public interface ContestComboRepository extends JpaRepository<ContestCombo, ContestComboKey> {
    ContestCombo findByFirstAttackAndSecondAttackAndGeneration(Attack firstAttack, Attack secondAttack, ContestType generation);
}
