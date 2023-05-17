package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Attack;
import com.pokemonurpg.entities.ContestCombo;
import com.pokemonurpg.entities.ContestComboKey;
import com.pokemonurpg.entities.ContestType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestComboRepository extends JpaRepository<ContestCombo, ContestComboKey> {
    ContestCombo findByFirstAttackAndSecondAttackAndGeneration(Attack firstAttack, Attack secondAttack, ContestType generation);
}
