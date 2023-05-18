package com.pokemonurpg.configuration.v1.sitemenuitems;

import com.pokemonurpg.infrastructure.v1.data.jpa.MenuItemRepository;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.entities.v1.MenuItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuItemService implements NamedObjectService<MenuItem> {
    @Resource
    MenuItemRepository menuItemRepository;

    @Override
    public MenuItem findByName(String name) {
        return findByNameExact(name);
    }

    @Override
    public MenuItem findByNameExact(String name) {
        return menuItemRepository.findByName(name);
    }
}
