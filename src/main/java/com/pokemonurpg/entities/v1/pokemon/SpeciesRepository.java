package com.pokemonurpg.entities.v1.pokemon;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pokemonurpg.entities.v1.shared.NamedRepository;

@Repository
public interface SpeciesRepository extends NamedRepository<Species> {
    @Query("select max(dexno) from Species s")
    Integer findMaxDexno();
    List<Species> findByDexno(Integer dexno);
    Species findFirstByDexno(int dexno);
    List<Species> findByPreEvolution(Species species);
    List<Species> findByPreMega(Species species);
}