package com.pokemonurpg.configuration.v1.member.member.model;

import com.pokemonurpg.configuration.v1.member.member.input.OwnedItemInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.OwnedItem;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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