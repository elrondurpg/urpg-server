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
    @Query("select s.name from Species s " +
            "where s.preMega is null " +
            "and s.battleOnly is false ")
    List<String> findAllOwnableNames();
    @Query("select s.name from Species s " +
            "where s.preEvolution is null " +
            "and s.legendaryTier = 0 " +
            "and s.preMega is null " +
            "and s in (select s2.preEvolution from Species s2)")
    List<String> findAllStarterNames();
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