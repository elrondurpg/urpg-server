package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.entities.v1.ContestCombo;
import com.pokemonurpg.entities.v1.ContestComboKey;
import com.pokemonurpg.entities.v1.ContestType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestComboRepository extends JpaRepository<ContestCombo, ContestComboKey> {
    ContestCombo findByFirstAttackAndSecondAttackAndGeneration(Attack firstAttack, Attack secondAttack, ContestType generation);
}
