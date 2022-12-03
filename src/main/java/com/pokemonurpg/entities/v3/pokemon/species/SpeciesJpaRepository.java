package com.pokemonurpg.entities.v3.pokemon.species;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.entities.v3.shared.NamedRepository;

@Repository
public interface SpeciesJpaRepository extends NamedRepository<SpeciesEntity> {
    @Query("select max(dexno) from Species s")
    Integer findMaxDexno();
    List<Species> findByDexno(Integer dexno);
    Species findFirstByDexno(int dexno);
    List<Species> findByPreEvolution(Species species);
    List<Species> findByPreMega(Species species);
}