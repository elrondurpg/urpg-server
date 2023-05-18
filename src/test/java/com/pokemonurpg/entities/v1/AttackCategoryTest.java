package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryInputDto;
import com.pokemonurpg.entities.v1.AttackCategory;
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