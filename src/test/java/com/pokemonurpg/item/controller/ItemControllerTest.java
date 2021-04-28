package com.pokemonurpg.item.controller;

import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.input.ItemInputDto;
import com.pokemonurpg.item.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;
    private final static List<String> TYPES = Arrays.asList("TM", "HM");
    private final static List<Item> ITEMS = Arrays.asList(new Item(), new Item());

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    private Item item = new Item();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(itemService.findAllNames()).thenReturn(names);
        assertEquals(names, itemController.findAllNames());
    }

    @Test
    public void findByName() {
        when(itemService.findByName(NAME)).thenReturn(item);
        assertEquals(item, itemController.findByName(NAME));
    }

    @Test
    public void findByTypeIn() {
        when(itemService.findByTypeIn(TYPES)).thenReturn(ITEMS);
        assertEquals(ITEMS, itemController.findByTypeIn(TYPES));
    }

    @Test
    public void create() {
        ItemInputDto input = new ItemInputDto();
        input.setName(NAME);
        when(itemService.create(input)).thenReturn(item);
        assertEquals(item, itemController.create(input));
    }

    @Test
    public void update() {
        ItemInputDto input = new ItemInputDto();
        input.setName(NAME);
        when(itemService.update(input, DBID)).thenReturn(item);
        assertEquals(item, itemController.update(input, DBID));
    }

}