package com.pokemonurpg.configuration.v1.site.menu;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.entities.v1.site.MenuItem;
import com.pokemonurpg.entities.v1.site.MenuItemRepository;

@Service
public class MenuItemService {
    @Resource
    MenuItemRepository menuItemRepository;

    public MenuItem findByName(String name) {
        return findByNameExact(name);
    }

    public MenuItem findByNameExact(String name) {
        return menuItemRepository.findByName(name);
    }
}
