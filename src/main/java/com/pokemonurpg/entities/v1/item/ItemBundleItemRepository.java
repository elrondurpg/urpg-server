package com.pokemonurpg.entities.v1.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemBundleItemRepository  extends JpaRepository<ItemBundleItem, ItemBundleItemKey> {
    List<ItemBundleItem> findByBundle(ItemBundle bundle);
    ItemBundleItem findByBundleAndItem(ItemBundle bundle, Item item);
}
