package com.pokemonurpg.site.repository;

import com.pokemonurpg.site.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    MenuItem findByDbid(int dbid);
    MenuItem findByName(String name);
}
