package com.pokemonurpg.configuration.v1.item.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.item.input.ItemInputDto;
import com.pokemonurpg.entities.v1.item.Item;
import com.pokemonurpg.entities.v1.item.ItemRepository;

@Service
public class ItemService {

    @Resource
    private ItemRepository itemRepository;

    // public List<String> findNamesBy(String type) {
    //     if (type == null || "".equals(type)) {
    //         return itemRepository.findAllNames();
    //     }
    //     else return itemRepository.findByTypeIn(Arrays.asList(type.split(","))).stream().map(Item::getName).collect(Collectors.toList());
    // }

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

    public Item findByNameExact(String name) {
        return itemRepository.findByName(name);
    }

    // public List<Item> findByTypeIn(List<String> types) {
    //     List<Item> items = itemRepository.findByTypeIn(types);
    //     if (items != null && items.isEmpty())
    //         return null;
    //     else return items;
    // }

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
