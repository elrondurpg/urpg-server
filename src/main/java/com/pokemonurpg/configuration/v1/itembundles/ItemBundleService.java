package com.pokemonurpg.configuration.v1.itembundles;

import java.util.List;

import javax.annotation.Resource;

import com.pokemonurpg.infrastructure.data.ItemBundleRepository;
import org.springframework.stereotype.Service;

import com.pokemonurpg.lib.service.NamedObjectService;
import com.pokemonurpg.entities.ItemBundle;

@Service
public class ItemBundleService implements NamedObjectService<ItemBundle> {
    @Resource
    private ItemBundleRepository itemBundleRepository;

    @Resource
    private ItemBundleItemService itemBundleItemService;

    public List<String> findAllNames() {
        return itemBundleRepository.findAllNames();
    }

    public ItemBundle findByName(String name) {
        ItemBundle bundle = findByNameExact(name);
        if (bundle == null && name != null) {
            return itemBundleRepository.findFirstByNameStartingWith(name);
        }
        else return bundle;
    }

    public ItemBundle findByNameExact(String name) {
        return itemBundleRepository.findByName(name);
    }

    public ItemBundle create(ItemBundleInputDto input) {
        ItemBundle bundle = new ItemBundle(input);
        itemBundleRepository.save(bundle);

        updateAssociatedValues(bundle, input);

        return bundle;
    }

    public ItemBundle update(ItemBundleInputDto input, int dbid) {
        ItemBundle bundle = itemBundleRepository.findByDbid(dbid);
        if (bundle != null) {
            bundle.update(input);
            itemBundleRepository.save(bundle);
            updateAssociatedValues(bundle, input);
        }
        return bundle;
    }

    void updateAssociatedValues(ItemBundle bundle, ItemBundleInputDto input) {
        updateItemBundleItems(bundle, input);
    }

    void updateItemBundleItems(ItemBundle bundle, ItemBundleInputDto input) {
        List<ItemBundleItemInputDto> items = input.getItems();
        for (ItemBundleItemInputDto item : items) {
            itemBundleItemService.update(bundle, item);
        }
        bundle.setItems(itemBundleItemService.findByBundle(bundle));
    }
}
