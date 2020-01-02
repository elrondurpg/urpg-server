package com.pokemonurpg.repository;

import com.pokemonurpg.object.battle.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    @Query("select t.name from Weather t")
    List<Object> findAllNames();
    Weather findByDbid(int dbid);
    Weather findByName(String name);
    List<Weather> findByNameStartingWith(String name);
}
