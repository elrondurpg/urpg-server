package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.Battle;
import com.pokemonurpg.object.pokemon.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BattleRepository extends JpaRepository<Battle, Long> {
    Battle findByDbid(Long dbid);
}

