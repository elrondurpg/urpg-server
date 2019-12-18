package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.dto.stats.response.OwnedItemDto;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.ItemRepository;
import com.pokemonurpg.repository.OwnedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnedItemService {
    private ItemRepository itemRepository;
    private OwnedItemRepository ownedItemRepository;
    private LogService logService;

    @Autowired
    public OwnedItemService(ItemRepository itemRepository, OwnedItemRepository ownedItemRepository, LogService logService) {
        this.itemRepository = itemRepository;
        this.ownedItemRepository = ownedItemRepository;
        this.logService = logService;
    }

    public void create(Member member, OwnedItemDto input) {
        Item item = itemRepository.findByName(input.getName());
        if (item != null) {
            OwnedItem ownedItem = new OwnedItem(member.getDbid(), item.getDbid(), input.getQuantity());
            ownedItemRepository.save(ownedItem);
            logService.log(member, member.getUsername() + " gained " + input.getQuantity() + "x " + input.getName());
        }
    }

    public void update(OwnedItem existingRecord, OwnedItemDto input) {
        if (existingRecord != null && existingRecord.getTrainer() != null) {
            Member member = existingRecord.getTrainer();
            int current = existingRecord.getQuantity();

            if (input.getQuantity() == 0) {
                ownedItemRepository.delete(existingRecord);
            } else {
                existingRecord.setQuantity(input.getQuantity());
                ownedItemRepository.save(existingRecord);
            }

            int difference = input.getQuantity() - current;
            if (difference > 0) {
                logService.log(member, member.getUsername() + " gained " + difference + "x " + input.getName());
            } else if (difference < 0) {
                logService.log(member, member.getUsername() + " lost " + (difference * -1) + "x " + input.getName());
            }
        }
    }

    public void updateAll(Member member, List<OwnedItemDto> input) {
        if (input != null) {
            for (OwnedItemDto record : input) {
                Item item = itemRepository.findByName(record.getName());

                OwnedItem existingRecord = ownedItemRepository.findByIdTrainerDbidAndIdItemDbid(member.getDbid(), item.getDbid());
                if (existingRecord != null) {
                    update(existingRecord, record);
                } else if (record.getQuantity() > 0) {
                    create(member, record);
                }
            }
        }
    }
}
