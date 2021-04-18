package com.pokemonurpg.site.repository;

import com.pokemonurpg.site.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findAllByOrderByPositionAsc();
    MenuItem findByName(String name);
}
