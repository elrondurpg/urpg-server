package com.pokemonurpg.repository;

import com.pokemonurpg.object.OwnedExtraMove;
import com.pokemonurpg.object.OwnedExtraMoveKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedExtraMoveRepository extends JpaRepository<OwnedExtraMove, OwnedExtraMoveKey> {
    OwnedExtraMove findByIdPokemonDbidAndIdAttackDbid(int pokemonDbid, int attackDbid);
}
