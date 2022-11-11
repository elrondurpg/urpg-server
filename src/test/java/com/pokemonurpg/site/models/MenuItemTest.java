package com.pokemonurpg.site.models;

import com.pokemonurpg.site.input.MenuItemInputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MenuItemTest {
    private final static String NAME = "NAME";
    private final static String URL = "URL";
    private final static Integer POSITION = 3432;
    private final static Integer INDENT = 1;

    @Test
    public void testPojo() {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(NAME);
        menuItem.setUrl(URL);
        menuItem.setPosition(POSITION);
        menuItem.setIndent(INDENT);

        assertEquals(NAME, menuItem.getName());
        assertEquals(URL, menuItem.getUrl());
        assertEquals(POSITION, menuItem.getPosition());
        assertEquals(INDENT, menuItem.getIndent());
    }

    @Test
    public void testConstructor() {
        MenuItemInputDto input = new MenuItemInputDto();
        input.setIndent(INDENT);
        input.setName(NAME);
        input.setUrl(URL);
        input.setPosition(POSITION);

        MenuItem menuItem = new MenuItem(input);
        assertEquals(NAME, menuItem.getName());
        assertEquals(URL, menuItem.getUrl());
        assertEquals(POSITION, menuItem.getPosition());
        assertEquals(INDENT, menuItem.getIndent());
    }
}