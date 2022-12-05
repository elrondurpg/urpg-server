package com.pokemonurpg.entities.v1.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonurpg.entities.v1.item.Item;

public interface OwnedItemRepository  extends JpaRepository<OwnedItem, OwnedItemKey> {
    OwnedItem findByTrainerAndItem(Member trainer, Item item);
}
