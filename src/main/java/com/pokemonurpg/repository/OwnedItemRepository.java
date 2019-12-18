package com.pokemonurpg.repository;

import com.pokemonurpg.object.OwnedItem;
import com.pokemonurpg.object.OwnedItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnedItemRepository  extends JpaRepository<OwnedItem, OwnedItemKey> {
    OwnedItem findByIdTrainerDbidAndIdItemDbid(int trainerDbid, int itemDbid);
}
