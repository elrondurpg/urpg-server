package com.pokemonurpg.repository;

import com.pokemonurpg.object.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    Species findByName(String name);
    Species findByDbid(Integer dbid);
    List<Species> findByNameStartingWith(String name);
    List<Species> findByDexno(Integer dexno);
}