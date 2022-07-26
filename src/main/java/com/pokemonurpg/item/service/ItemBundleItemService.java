package com.pokemonurpg.item.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.item.input.ItemBundleItemInputDto;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.models.ItemBundle;
import com.pokemonurpg.item.models.ItemBundleItem;
import com.pokemonurpg.item.repository.ItemBundleItemRepository;
import com.pokemonurpg.item.repository.ItemRepository;

@Service
public class ItemBundleItemService {
    @Resource
    private ItemBundleItemRepository itemBundleItemRepository;

    @Resource
    private ItemRepository itemRepository;

    public void update(ItemBundle bundle, ItemBundleItemInputDto input) {
        Item item = itemRepository.findByName(input.getItem());
        ItemBundleItem existingRecord = itemBundleItemRepository.findByBundleAndItem(bundle, item);
        if (existingRecord != null) {
            if (input.getDelete()) {
                itemBundleItemRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                itemBundleItemRepository.save(existingRecord);
            }
        }
        else {
            ItemBundleItem newRecord = new ItemBundleItem(input, bundle, item);
            itemBundleItemRepository.save(newRecord);
        }
    }
}
