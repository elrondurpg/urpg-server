package com.pokemonurpg.site.service;

import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.site.models.MenuItem;
import com.pokemonurpg.site.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuItemService implements NamedObjectService<MenuItem> {
    @Resource
    MenuItemRepository menuItemRepository;

    @Override
    public MenuItem findByName(String name) {
        return menuItemRepository.findByName(name);
    }
}
