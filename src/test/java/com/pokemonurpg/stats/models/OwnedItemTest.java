package com.pokemonurpg.stats.models;

import com.pokemonurpg.entities.Item;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.input.OwnedItemInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class OwnedItemTest {
    private final static Integer QUANTITY = 432;
    private final static Integer MEMBER_DBID = 42212;
    private final static Integer ITEM_DBID = 41123;

    @Test
    public void testPojo() {
        assertNotNull(new OwnedItem());
    }

    @Test
    public void testConstructor() {
        OwnedItemInputDto input = new OwnedItemInputDto();
        input.setQuantity(QUANTITY);

        Member trainer = new Member();
        trainer.setDbid(MEMBER_DBID);

        Item item = new Item();
        item.setDbid(ITEM_DBID);

        OwnedItem ownedItem = new OwnedItem(input, trainer, item);
        assertEquals(QUANTITY, ownedItem.getQuantity());
        assertEquals(trainer, ownedItem.getTrainer());
        assertEquals(item, ownedItem.getItem());
        assertEquals(MEMBER_DBID, ownedItem.id.getTrainerDbid());
        assertEquals(ITEM_DBID, ownedItem.id.getItemDbid());
    }

}