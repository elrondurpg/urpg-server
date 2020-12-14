package com.pokemonurpg.attack.models;

import com.pokemonurpg.attack.input.AttackCategoryInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttackCategoryTest {
    private final static Integer DBID = 23489;
    private final static String NAME = "TEST";

    private AttackCategory category = new AttackCategory();

    @Test
    public void testPojo() {
        category.setDbid(DBID);
        assertEquals(DBID, category.getDbid());

        category.setName(NAME);
        assertEquals(NAME, category.getName());
    }

    @Test
    public void testConstructor() {
        AttackCategoryInputDto input = new AttackCategoryInputDto();
        input.setName(NAME);

        AttackCategory category = new AttackCategory(input);
        assertEquals(NAME, category.getName());
    }

}