package com.pokemonurpg.repository;

import com.pokemonurpg.object.AlteredFormMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlteredFormMethodRepository extends JpaRepository<AlteredFormMethod, Integer> {
    Optional<AlteredFormMethod> findByDexno(Integer dexno);
}
