package com.pokemonurpg.ability.controller;

import com.pokemonurpg.configuration.v1.abilities.AbilityController;
import com.pokemonurpg.entities.Ability;
import com.pokemonurpg.configuration.v1.abilities.AbilityInputDto;
import com.pokemonurpg.configuration.v1.abilities.AbilityService;
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
public class AbilityControllerTest {
    private static final String ABILITY_NAME = "TEST";
    private static final Integer DBID = 23423;

    @InjectMocks
    private AbilityController abilityController;

    @Mock
    private AbilityService abilityService;

    @Mock
    private Ability ability;

    @Test
    public void findReturnsResultFromAbilityService() {
        when(abilityService.findByName(ABILITY_NAME)).thenReturn(ability);
        Ability ability = abilityController.findByName(ABILITY_NAME);
        assertNotNull(ability);
    }

    @Test
    public void createReturnsResultFromAbilityService() {
        AbilityInputDto input = new AbilityInputDto();
        Ability ability = new Ability();

        when (abilityService.create(input)).thenReturn(ability);
        assertEquals(ability, abilityController.create(input));
    }

    @Test
    public void updateReturnsResultFromAbilityService() {
        AbilityInputDto input = new AbilityInputDto();
        Ability ability = new Ability();

        when (abilityService.update(input, DBID)).thenReturn(ability);
        assertEquals(ability, abilityController.update(input, DBID));
    }

    @Test
    public void getAllAbilities() {
        List<String> names = new ArrayList<>();
        when(abilityService.findAllNames()).thenReturn(names);

        assertEquals(names, abilityController.findAllNames());
    }
}