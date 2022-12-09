package com.pokemonurpg.configuration.v1.member.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.member.member.input.OwnedItemInputDto;
import com.pokemonurpg.entities.v1.item.Item;
import com.pokemonurpg.entities.v1.item.ItemRepository;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.member.OwnedItem;
import com.pokemonurpg.entities.v1.member.OwnedItemRepository;

@Service
public class OwnedItemService {

    @Resource
    private OwnedItemRepository ownedItemRepository;

    @Resource
    private ItemRepository itemRepository;

    public OwnedItem add(Member member, String name, int quantity) {
        Item item = itemRepository.findByName(name);
        OwnedItem itemRecord = ownedItemRepository.findByTrainerAndItem(member, item);
        if (itemRecord != null) {
            itemRecord.update(quantity);
            ownedItemRepository.save(itemRecord);
        }
        else {
            OwnedItem newRecord = new OwnedItem(member, item, quantity);
            ownedItemRepository.save(newRecord);
        }
        return itemRecord;
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
