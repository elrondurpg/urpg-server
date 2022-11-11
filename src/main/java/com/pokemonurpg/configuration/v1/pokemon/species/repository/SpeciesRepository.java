package com.pokemonurpg.configuration.v1.pokemon.species.repository;

import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends NamedConfigurationRepository<Species> {
    @Query("select max(dexno) from Species s")
    Integer findMaxDexno();
    List<Species> findByDexno(Integer dexno);
    Species findFirstByDexno(int dexno);
    List<Species> findByPreEvolution(Species species);
    List<Species> findByPreMega(Species species);
}