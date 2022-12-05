package com.pokemonurpg.item.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.item.input.ItemInputDto;
import com.pokemonurpg.configuration.v1.item.models.Item;
import com.pokemonurpg.configuration.v1.item.repository.ItemRepository;
import com.pokemonurpg.configuration.v1.item.service.ItemService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";
    private final static List<String> TYPES = Arrays.asList("TM", "HM");
    private final static List<Item> ITEMS = Arrays.asList(new Item(), new Item());

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    private Item item = new Item();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(itemRepository.findAllNames()).thenReturn(types);

        assertEquals(types, itemService.findNamesBy(null));
    }

    @Test
    public void findByDbid() {
        when(itemRepository.findByDbid(DBID)).thenReturn(item);
        assertEquals(item, itemService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(itemRepository.findByName(NAME)).thenReturn(item);
        assertEquals(item, itemService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(itemRepository.findByName(NAME)).thenReturn(null);
        when(itemRepository.findFirstByNameStartingWith(NAME)).thenReturn(item);
        assertEquals(item, itemService.findByName(NAME));
    }

    @Test
    public void findByTypeIn() {
        when(itemRepository.findByTypeIn(TYPES)).thenReturn(ITEMS);
        assertEquals(ITEMS, itemService.findByTypeIn(TYPES));
    }

    @Test
    public void findByTypeInReturnsNull() {
        when(itemRepository.findByTypeIn(TYPES)).thenReturn(new ArrayList<Item> ());
        assertNull(itemService.findByTypeIn(TYPES));
    }

    @Test
    public void create() {
        ItemInputDto input = new ItemInputDto();
        input.setName(NAME);

        Item item = itemService.create(input);
        assertEquals(NAME, item.getName());
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    public void updateExistingRecord() {
        ItemInputDto input = new ItemInputDto();
        input.setName(NAME);

        when(itemRepository.findByDbid(DBID)).thenReturn(item);

        Item item1 = itemService.update(input, DBID);
        assertEquals(item, item1);
        assertEquals(NAME, item1.getName());
        verify(itemRepository, times(1)).save(item1);
    }

    @Test
    public void updateNonExistingRecord() {
        ItemInputDto input = new ItemInputDto();
        input.setName(NAME);

        when(itemRepository.findByDbid(DBID)).thenReturn(null);

        Item item1 = itemService.update(input, DBID);
        assertNull(item1);
        verify(itemRepository, times(0)).save(ArgumentMatchers.any());
    }

}