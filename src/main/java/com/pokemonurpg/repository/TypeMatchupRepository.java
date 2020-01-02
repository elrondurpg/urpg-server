package com.pokemonurpg.repository;

import com.pokemonurpg.object.pokemon.TypeMatchup;
import com.pokemonurpg.object.pokemon.TypeMatchupKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeMatchupRepository extends JpaRepository<TypeMatchup, TypeMatchupKey> {
    TypeMatchup findByIdAttackTypeDbidAndIdDefendTypeDbid(int attackTypeDbid, int defendTypeDbid);
}
