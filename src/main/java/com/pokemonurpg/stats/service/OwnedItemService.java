package com.pokemonurpg.stats.service;

import com.pokemonurpg.entities.Item;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.infrastructure.data.ItemRepository;
import com.pokemonurpg.stats.input.OwnedItemInputDto;
import com.pokemonurpg.stats.repository.OwnedItemRepository;
import com.pokemonurpg.stats.models.OwnedItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OwnedItemService {

    @Resource
    private OwnedItemRepository ownedItemRepository;

    @Resource
    private ItemRepository itemRepository;

    public void add(Member member, String name, int quantity) {
        Item item = itemRepository.findByName(name);
        OwnedItem existingRecord = ownedItemRepository.findByTrainerAndItem(member, item);
        if (existingRecord != null) {
            existingRecord.update(quantity);
            ownedItemRepository.save(existingRecord);
        }
        else {
            OwnedItem newRecord = new OwnedItem(member, item, quantity);
            ownedItemRepository.save(newRecord);
        }
    }

    public void update(Member member, OwnedItemInputDto input) {
        Item item = itemRepository.findByName(input.getItem());
        OwnedItem existingRecord = ownedItemRepository.findByTrainerAndItem(member, item);
        if (existingRecord != null) {
            if (input.getDelete()) {
                ownedItemRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                ownedItemRepository.save(existingRecord);
            }
        }
        else {
            OwnedItem newRecord = new OwnedItem(input, member, item);
            ownedItemRepository.save(newRecord);
        }
    }
}
