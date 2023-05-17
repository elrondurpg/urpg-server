package com.pokemonurpg.attack.controller;

import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryController;
import com.pokemonurpg.entities.AttackCategory;
import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryInputDto;
import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AttackCategoryControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private AttackCategoryController attackCategoryController;

    @Mock
    private AttackCategoryService attackCategoryService;

    private AttackCategory category = new AttackCategory();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(attackCategoryService.findAllNames()).thenReturn(names);
        assertEquals(names, attackCategoryController.findAllNames());
    }

    @Test
    public void findByName() {
        when(attackCategoryService.findByName(NAME)).thenReturn(category);
        assertEquals(category, attackCategoryController.findByName(NAME));
    }

    @Test
    public void create() {
        AttackCategoryInputDto input = new AttackCategoryInputDto();
        input.setName(NAME);
        when(attackCategoryService.create(input)).thenReturn(category);
        assertEquals(category, attackCategoryController.create(input));
    }

    @Test
    public void update() {
        AttackCategoryInputDto input = new AttackCategoryInputDto();
        input.setName(NAME);
        when(attackCategoryService.update(input, DBID)).thenReturn(category);
        assertEquals(category, attackCategoryController.update(input, DBID));
    }
}