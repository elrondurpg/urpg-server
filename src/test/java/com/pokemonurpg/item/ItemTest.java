package com.pokemonurpg.item;

import com.pokemonurpg.configuration.v1.items.ItemInputDto;
import com.pokemonurpg.entities.v1.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    private final static Integer DBID = 32432;
    public static final String NAME = "TEST_NAME";
    public static final Integer PRICE = 10000;
    public static final String TYPE = "TM";
    public static final String DESCRIPTION = "This is a test.";

    @Test
    public void testPojo() {
        Item item = new Item();
        item.setDbid(DBID);
        assertEquals(DBID, item.getDbid());
    }

    @Test
    public void createItemFromDto() {
        ItemInputDto input = new ItemInputDto();
        input.setName(NAME);
        input.setPrice(PRICE);
        input.setType(TYPE);
        input.setDescription(DESCRIPTION);

        Item item = new Item(input);
        assertEquals(NAME, item.getName());
        assertEquals(PRICE, item.getPrice());
        assertEquals(TYPE, item.getType());
        assertEquals(DESCRIPTION, item.getDescription());
    }

}