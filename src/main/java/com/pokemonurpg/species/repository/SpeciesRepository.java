package com.pokemonurpg.species.repository;

import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.creative.models.StoryRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    @Query("select max(dexno) from Species s")
    Integer findMaxDexno();
    @Query("select s.name from Species s")
    List<String> findAllNames();
    Species findByName(String name);
    Species findByDbid(Integer dbid);
    List<Species> findByDexno(Integer dexno);
    Species findFirstByNameStartingWith(String name);
    Species findFirstByDexno(int dexno);
    List<Species> findByStoryRank(StoryRank rank);
    List<Species> findByParkRank(ParkRank rank);
    List<Species> findByPreEvolution(Species species);
    List<Species> findByPreMega(Species species);
}