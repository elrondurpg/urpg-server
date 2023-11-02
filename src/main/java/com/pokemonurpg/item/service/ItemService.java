package com.pokemonurpg.item.service;

import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.input.ItemInputDto;
import com.pokemonurpg.item.repository.ItemRepository;
import com.pokemonurpg.core.service.NamedObjectService;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService implements NamedObjectService<Item> {

    @Resource
    private ItemRepository itemRepository;

    public List<String> findNamesBy(String type) {
        if (type == null || "".equals(type)) {
            return itemRepository.findAllNames();
        }
        else return itemRepository.findByTypeIn(Arrays.asList(type.split(","))).stream().map(Item::getName).collect(Collectors.toList());
    }

    public Item findByDbid(int dbid) {
        return itemRepository.findByDbid(dbid);
    }

    public Item findByName(String name) {
        Item item = findByNameExact(name);
        if (item == null && name != null) {
            return itemRepository.findFirstByNameStartingWith(name);
        }
        else return item;
    }

    @Override
    public Item findByNameExact(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> findByTypeIn(List<String> types) {
        List<Item> items = itemRepository.findByTypeIn(types);
        if (items != null && items.isEmpty())
            return null;
        else return items;
    }

    public Item create(ItemInputDto input) {
        Item item = new Item(input);
        itemRepository.save(item);
        return item;
    }

    public Item update(ItemInputDto input, int dbid) {
        Item item = itemRepository.findByDbid(dbid);
        if (item != null) {
            item.update(input);
            itemRepository.save(item);
        }
        return item;
    }
}