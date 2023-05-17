package com.pokemonurpg.stats.repository;

import com.pokemonurpg.entities.Item;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.models.OwnedItem;
import com.pokemonurpg.stats.models.OwnedItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedItemRepository  extends JpaRepository<OwnedItem, OwnedItemKey> {
    OwnedItem findByTrainerAndItem(Member trainer, Item item);
}
