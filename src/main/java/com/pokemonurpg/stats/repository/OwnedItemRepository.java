package com.pokemonurpg.stats.repository;

import com.pokemonurpg.configuration.v1.member.member.model.OwnedItem;
import com.pokemonurpg.configuration.v1.member.member.model.OwnedItemKey;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedItemRepository  extends JpaRepository<OwnedItem, OwnedItemKey> {
    OwnedItem findByTrainerAndItem(Member trainer, Item item);
}
