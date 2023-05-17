package com.pokemonurpg.attack.service;

import com.pokemonurpg.entities.AttackCategory;
import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryInputDto;
import com.pokemonurpg.infrastructure.data.AttackCategoryRepository;
import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AttackCategoryServiceTest {
    private final static AttackCategory ATTACK_CATEGORY = mock(AttackCategory.class);
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private AttackCategoryService attackCategoryService;

    @Mock
    private AttackCategoryRepository attackCategoryRepository;

    AttackCategory category = new AttackCategory();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> categories = new ArrayList<>();
        when(attackCategoryRepository.findAllNames()).thenReturn(categories);

        assertEquals(categories, attackCategoryService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(attackCategoryRepository.findByDbid(DBID)).thenReturn(category);
        assertEquals(category, attackCategoryService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(attackCategoryRepository.findByName(NAME)).thenReturn(category);
        assertEquals(category, attackCategoryService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(attackCategoryRepository.findByName(NAME)).thenReturn(null);
        when(attackCategoryRepository.findFirstByNameStartingWith(NAME)).thenReturn(category);
        assertEquals(category, attackCategoryService.findByName(NAME));
    }

    @Test
    public void findByNameExact() {
        when(attackCategoryRepository.findByName(NAME)).thenReturn(ATTACK_CATEGORY);
        assertEquals(ATTACK_CATEGORY, attackCategoryService.findByNameExact(NAME));
    }

    @Test
    public void create() {
        AttackCategoryInputDto input = new AttackCategoryInputDto();
        input.setName(NAME);

        AttackCategory category = attackCategoryService.create(input);
        assertEquals(NAME, category.getName());
        verify(attackCategoryRepository, times(1)).save(category);
    }

    @Test
    public void updateExistingRecord() {
        AttackCategoryInputDto input = new AttackCategoryInputDto();
        input.setName(NAME);

        when(attackCategoryRepository.findByDbid(DBID)).thenReturn(category);

        AttackCategory category2 = attackCategoryService.update(input, DBID);
        assertEquals(category, category2);
        assertEquals(NAME, category2.getName());
        verify(attackCategoryRepository, times(1)).save(category2);
    }

    @Test
    public void updateNonExistingRecord() {
        AttackCategoryInputDto input = new AttackCategoryInputDto();
        input.setName(NAME);

        when(attackCategoryRepository.findByDbid(DBID)).thenReturn(null);

        AttackCategory category2 = attackCategoryService.update(input, DBID);
        assertNull(category2);
        verify(attackCategoryRepository, times(0)).save(Matchers.any());
    }
}