package com.pokemonurpg.stats.service;

import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.item.repository.ItemRepository;
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
