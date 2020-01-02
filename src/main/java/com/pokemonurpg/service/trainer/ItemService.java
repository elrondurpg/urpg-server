package com.pokemonurpg.service.trainer;

import com.pokemonurpg.object.trainer.Item;
import com.pokemonurpg.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Object> findAll() {
        return itemRepository.findAllNames();
    }

    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }
}
