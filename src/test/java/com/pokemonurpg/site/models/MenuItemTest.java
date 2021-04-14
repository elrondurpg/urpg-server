package com.pokemonurpg.site.models;

import com.pokemonurpg.site.input.MenuItemInputDto;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MenuItemTest {
    private final static Integer DBID = 234;
    private final static MenuItem PARENT = mock(MenuItem.class);
    private final static String NAME = "NAME";
    private final static String URL = "URL";
    private final static Integer POSITION = 3432;

    @Test
    public void testPojo() {
        MenuItem menuItem = new MenuItem();
        menuItem.setDbid(DBID);
        menuItem.setParent(PARENT);
        menuItem.setName(NAME);
        menuItem.setUrl(URL);
        menuItem.setPosition(POSITION);

        assertEquals(DBID, menuItem.getDbid());
        assertEquals(PARENT, menuItem.getParent());
        assertEquals(NAME, menuItem.getName());
        assertEquals(URL, menuItem.getUrl());
        assertEquals(POSITION, menuItem.getPosition());
    }

    @Test
    public void testConstructor() {
        MenuItemInputDto input = new MenuItemInputDto();
        input.setName(NAME);
        input.setUrl(URL);
        input.setPosition(POSITION);

        MenuItem menuItem = new MenuItem(input);
        assertEquals(NAME, menuItem.getName());
        assertEquals(URL, menuItem.getUrl());
        assertEquals(POSITION, menuItem.getPosition());
    }
}