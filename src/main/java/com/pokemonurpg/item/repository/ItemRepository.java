package com.pokemonurpg.item.repository;

import com.pokemonurpg.item.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("select t.name from Item t")
    List<String> findAllNames();
    Item findByDbid(int dbid);
    Item findByName(String name);
    List<Item> findByTypeIn(List<String> types);
    Item findFirstByNameStartingWith(String name);
}
