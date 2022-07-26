package com.pokemonurpg.item.repository;

import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.models.ItemBundle;
import com.pokemonurpg.item.models.ItemBundleItem;
import com.pokemonurpg.item.models.ItemBundleItemKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemBundleItemRepository  extends JpaRepository<ItemBundleItem, ItemBundleItemKey> {
    List<ItemBundleItem> findByBundle(ItemBundle bundle);
    ItemBundleItem findByBundleAndItem(ItemBundle bundle, Item item);
}
