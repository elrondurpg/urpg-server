package com.pokemonurpg.repository;

import com.pokemonurpg.object.pokemon.AlteredFormMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlteredFormMethodRepository extends JpaRepository<AlteredFormMethod, Integer> {
    AlteredFormMethod findByDexno(Integer dexno);
}
