package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BattlePokemonAttackRepository extends JpaRepository<BattlePokemonAttack, BattlePokemonAttackKey> {
    Optional<BattlePokemonAttack> findById(BattlePokemonAttackKey key);
}
