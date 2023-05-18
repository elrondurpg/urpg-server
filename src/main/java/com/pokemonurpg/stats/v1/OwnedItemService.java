package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Item;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.infrastructure.v1.data.jpa.ItemRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.OwnedItemRepository;
import com.pokemonurpg.entities.v1.OwnedItem;
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

    public void update(Member member, OwnedItemRequest input) {
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
