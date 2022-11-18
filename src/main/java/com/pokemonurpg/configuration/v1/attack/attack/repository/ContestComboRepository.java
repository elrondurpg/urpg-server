package com.pokemonurpg.configuration.v1.attack.attack.repository;

import com.pokemonurpg.configuration.v1.attack.attack.model.Attack;
import com.pokemonurpg.configuration.v1.attack.attack.model.ContestCombo;
import com.pokemonurpg.configuration.v1.attack.attack.model.ContestComboKey;
import com.pokemonurpg.configuration.v1.contest.type.model.ContestType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestComboRepository extends JpaRepository<ContestCombo, ContestComboKey> {
    ContestCombo findByFirstAttackAndSecondAttackAndGeneration(Attack firstAttack, Attack secondAttack, ContestType generation);
}
