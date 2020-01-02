package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.BattleTrainer;
import com.pokemonurpg.object.battle.BattleTrainerKey;
import com.pokemonurpg.object.pokemon.SpeciesAttack;
import com.pokemonurpg.object.pokemon.SpeciesAttackKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BattleTrainerRepository  extends JpaRepository<BattleTrainer, BattleTrainerKey> {
    Optional<BattleTrainer> findById(BattleTrainerKey key);
}
