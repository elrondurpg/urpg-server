package com.pokemonurpg.configuration.v1.items;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemRequestTest {
    public static final String NAME = "TEST_NAME";
    public static final Integer PRICE = 10000;
    public static final String TYPE = "TM";
    public static final String DESCRIPTION = "This is a test.";

    private ItemRequest item = new ItemRequest();

    @Test
    public void setName() {
        item.setName(NAME);
        assertEquals(NAME, item.getName());
    }

    @Test
    public void setPrice() {
        item.setPrice(PRICE);
        assertEquals(PRICE, item.getPrice());
    }

    @Test
    public void setType() {
        item.setType(TYPE);
        assertEquals(TYPE, item.getType());
    }

    @Test
    public void setDescription() {
        item.setDescription(DESCRIPTION);
        assertEquals(DESCRIPTION, item.getDescription());
    }
}