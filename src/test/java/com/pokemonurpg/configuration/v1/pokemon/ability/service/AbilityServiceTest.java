package com.pokemonurpg.configuration.v1.pokemon.ability.service;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.input.AbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.ability.repository.AbilityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AbilityServiceTest {
    private final static Ability    ABILITY = mock(Ability.class);
    private final static String NAME = "ABILITY_NAME";
    private final static String DESCRIPTION = "DESCRIPTION TEST";
    private final static String DESCRIPTION_2 = "DIFFERENT DESCRIPTION";
    private final static Integer DBID = 23243;

    @InjectMocks
    private AbilityService abilityService;

    @Mock
    private AbilityRepository abilityRepository;

    /*@Test
    public void findAll() {
        List<String> list = new ArrayList<>();
        when(abilityRepository.findAllNames()).thenReturn(list);
        assertEquals(list, abilityService.findAllNames());
    }*/

    @Test
    public void findByDbid() {
        Ability ability = new Ability();
        when(abilityRepository.findByDbid(DBID)).thenReturn(ability);

        assertEquals(ability, abilityService.findByDbid(DBID));
    }

    @Test
    public void findByNameReturnsNull() {
        when(abilityRepository.findByName(NAME)).thenReturn(null);
        when(abilityRepository.findFirstByNameStartingWith(NAME)).thenReturn(null);

        assertNull(abilityService.findByName(NAME));
    }

    @Test
    public void findByNameReturnsExactMatch() {
        Ability ability = new Ability();
        ability.setName(NAME);

        when(abilityRepository.findByName(NAME)).thenReturn(ability);

        Ability returnedAbility = abilityService.findByName(NAME);

        assertNotNull(returnedAbility);
        assertEquals(NAME, returnedAbility.getName());
    }

    @Test
    public void findByNameReturnsFirstMatchStartingWith() {
        Ability ability = new Ability();
        ability.setName(NAME);

        when(abilityRepository.findByName(NAME)).thenReturn(null);
        when(abilityRepository.findFirstByNameStartingWith(NAME)).thenReturn(ability);

        Ability returnedRole = abilityService.findByName(NAME);

        assertNotNull(returnedRole);
        assertEquals(NAME, returnedRole.getName());
    }

    @Test
    public void findByNameExact() {
        when(abilityRepository.findByName(NAME)).thenReturn(ABILITY);
        assertEquals(ABILITY, abilityService.findByNameExact(NAME));
    }

    @Test
    public void create() {
        AbilityInputDto input = new AbilityInputDto();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        Ability ability = abilityService.create(input);
        assertEquals(NAME, ability.getName());
        assertEquals(DESCRIPTION, ability.getDescription());
    }

    @Test
    public void update() {
        AbilityInputDto input = new AbilityInputDto();
        input.setDescription(DESCRIPTION_2);

        Ability ability = new Ability();
        ability.setName(NAME);
        ability.setDescription(DESCRIPTION);

        when(abilityRepository.findByDbid(DBID)).thenReturn(ability);
        Ability updatedAbility = abilityService.update(input, DBID);

        assertEquals(DESCRIPTION_2, updatedAbility.getDescription());
    }

    @Test
    public void updateReturnsNullWhenNotFound() {
        AbilityInputDto input = new AbilityInputDto();
        input.setDescription(DESCRIPTION_2);

        Ability ability = new Ability();
        ability.setName(NAME);
        ability.setDescription(DESCRIPTION);

        when(abilityRepository.findByDbid(DBID)).thenReturn(null);
        Ability updatedAbility = abilityService.update(input, DBID);

        assertNull(updatedAbility);
    }
}