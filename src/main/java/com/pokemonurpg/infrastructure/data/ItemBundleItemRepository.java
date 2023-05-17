package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Item;
import com.pokemonurpg.entities.ItemBundle;
import com.pokemonurpg.entities.ItemBundleItem;
import com.pokemonurpg.entities.ItemBundleItemKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemBundleItemRepository  extends JpaRepository<ItemBundleItem, ItemBundleItemKey> {
    List<ItemBundleItem> findByBundle(ItemBundle bundle);
    ItemBundleItem findByBundleAndItem(ItemBundle bundle, Item item);
}
