package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ParkRank;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.StoryRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Pokemon, Integer> {
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
    Pokemon findByName(String name);
    Pokemon findByDbid(Integer dbid);
    List<Pokemon> findByDexno(Integer dexno);
    Pokemon findFirstByNameStartingWith(String name);
    Pokemon findFirstByDexno(int dexno);
    List<Pokemon> findByStoryRank(StoryRank rank);
    List<Pokemon> findByParkRank(ParkRank rank);
    List<Pokemon> findByPreEvolution(Pokemon pokemon);
    List<Pokemon> findByPreMega(Pokemon pokemon);
}