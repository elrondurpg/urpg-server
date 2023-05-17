package com.pokemonurpg.configuration.v1.itembundles;

import java.util.List;

import javax.annotation.Resource;

import com.pokemonurpg.entities.ItemBundle;
import com.pokemonurpg.entities.ItemBundleItem;
import com.pokemonurpg.infrastructure.data.ItemBundleItemRepository;
import com.pokemonurpg.infrastructure.data.ItemRepository;
import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.Item;

@Service
public class ItemBundleItemService {
    @Resource
    private ItemBundleItemRepository itemBundleItemRepository;

    @Resource
    private ItemRepository itemRepository;

    public List<ItemBundleItem> findByBundle(ItemBundle bundle) {
        return itemBundleItemRepository.findByBundle(bundle);
    }

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