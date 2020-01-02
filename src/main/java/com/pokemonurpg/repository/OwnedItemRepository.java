package com.pokemonurpg.repository;

import com.pokemonurpg.object.trainer.OwnedItem;
import com.pokemonurpg.object.trainer.OwnedItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedItemRepository  extends JpaRepository<OwnedItem, OwnedItemKey> {
    OwnedItem findByIdTrainerDbidAndIdItemDbid(int trainerDbid, int itemDbid);
}
