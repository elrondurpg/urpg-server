package com.pokemonurpg.site.service;

import com.pokemonurpg.site.models.MenuItem;
import com.pokemonurpg.site.repository.MenuItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuItemServiceTest {
    private final static String NAME = "NAME";

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    private MenuItem menuItem = new MenuItem();

    @Test
    public void findByName() {
        when(menuItemRepository.findByName(NAME)).thenReturn(menuItem);
        assertEquals(menuItem, menuItemService.findByName(NAME));
    }

}