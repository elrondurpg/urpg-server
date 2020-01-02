package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.Battle;
import com.pokemonurpg.object.battle.BattlePokemon;
import com.pokemonurpg.object.pokemon.Type;
import com.pokemonurpg.object.trainer.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BattlePokemonRepository extends JpaRepository<BattlePokemon, Integer> {
    BattlePokemon findByDbid(int dbid);
    List<BattlePokemon> findByTrainer(Member trainer);
}

