package com.pokemonurpg.configuration.v1.sitemenuitem;

import com.pokemonurpg.infrastructure.data.MenuItemRepository;
import com.pokemonurpg.lib.service.NamedObjectService;
import com.pokemonurpg.entities.MenuItem;
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
