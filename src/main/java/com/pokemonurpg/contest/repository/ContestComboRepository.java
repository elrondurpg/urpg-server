package com.pokemonurpg.contest.repository;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.contest.models.ContestCombo;
import com.pokemonurpg.contest.models.ContestComboKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestComboRepository extends JpaRepository<ContestCombo, ContestComboKey> {
    ContestCombo findByFirstAttackAndSecondAttackAndIdContestType(Attack firstAttack, Attack secondAttack, String contestType);
}