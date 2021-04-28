package com.pokemonurpg.item.service;

import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.input.ItemInputDto;
import com.pokemonurpg.item.repository.ItemRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemService implements NamedObjectService<Item> {

    @Resource
    private ItemRepository itemRepository;

    public List<String> findAllNames() {
        return itemRepository.findAllNames();
    }

    public Item findByDbid(int dbid) {
        return itemRepository.findByDbid(dbid);
    }

    public Item findByName(String name) {
        Item item = itemRepository.findByName(name);
        if (item == null && name != null) {
            return itemRepository.findFirstByNameStartingWith(name);
        }
        else return item;
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
