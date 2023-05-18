package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Item;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.OwnedItem;
import com.pokemonurpg.entities.v1.OwnedItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedItemRepository  extends JpaRepository<OwnedItem, OwnedItemKey> {
    OwnedItem findByTrainerAndItem(Member trainer, Item item);
}
