package com.pokemonurpg.repository;

import com.pokemonurpg.object.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("select t.name from Item t")
    List<Object> findAllNames();
    Item findByDbid(int dbid);
    Item findByName(String name);
    List<Item> findByNameStartingWith(String name);
}
