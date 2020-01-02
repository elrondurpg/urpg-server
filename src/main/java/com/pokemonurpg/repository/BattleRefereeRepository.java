package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.BattleReferee;
import com.pokemonurpg.object.battle.BattleRefereeKey;
import com.pokemonurpg.object.battle.BattleTrainer;
import com.pokemonurpg.object.battle.BattleTrainerKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BattleRefereeRepository extends JpaRepository<BattleReferee, BattleRefereeKey> {
    Optional<BattleReferee> findById(BattleRefereeKey key);
}
