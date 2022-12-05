package com.pokemonurpg.entities.v1.site;

import java.util.List;

import com.pokemonurpg.entities.v1.shared.NamedRepository;

public interface MenuItemRepository extends NamedRepository<MenuItem> {
    List<MenuItem> findAllByOrderByPositionAsc();
}
