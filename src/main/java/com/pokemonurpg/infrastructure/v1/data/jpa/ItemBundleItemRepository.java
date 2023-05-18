package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Item;
import com.pokemonurpg.entities.v1.ItemBundle;
import com.pokemonurpg.entities.v1.ItemBundleItem;
import com.pokemonurpg.entities.v1.ItemBundleItemKey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemBundleItemRepository  extends JpaRepository<ItemBundleItem, ItemBundleItemKey> {
    List<ItemBundleItem> findByBundle(ItemBundle bundle);
    ItemBundleItem findByBundleAndItem(ItemBundle bundle, Item item);
}
