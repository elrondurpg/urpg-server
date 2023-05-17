package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.ItemBundle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemBundleRepository extends JpaRepository<ItemBundle, Integer> {
    @Query("select t.name from ItemBundle t")
    List<String> findAllNames();
    ItemBundle findByDbid(int dbid);
    ItemBundle findByName(String name);
    ItemBundle findFirstByNameStartingWith(String name);
}
