package com.pokemonurpg.species.service;

import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.ability.repository.AbilityRepository;
import com.pokemonurpg.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.SpeciesAbility;
import com.pokemonurpg.species.repository.SpeciesAbilityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpeciesAbilityServiceTest {
    private final static String NAME = "NAME";
    private final static Ability ABILITY = new Ability();
    private final static Species SPECIES = new Species();

    @InjectMocks
    private SpeciesAbilityService speciesAbilityService;
    
    @Mock
    private SpeciesAbilityRepository speciesAbilityRepository;

    @Mock
    private AbilityRepository abilityRepository;

    @Test
    public void deleteWhenFormExistsAndDeleteIsTrue() {
        SpeciesAbility speciesAbility = mock(SpeciesAbility.class);

        SpeciesAbilityInputDto input = new SpeciesAbilityInputDto();
        input.setName(NAME);
        input.setDelete(true);

        when(abilityRepository.findByName(NAME)).thenReturn(ABILITY);
        when(speciesAbilityRepository.findBySpeciesAndAbility(SPECIES, ABILITY)).thenReturn(speciesAbility);

        speciesAbilityService.update(SPECIES, input);

        verify(speciesAbilityRepository, times(1)).delete(speciesAbility);
    }

    @Test
    public void updateWhenFormExistsAndDeleteIsFalse() {
        SpeciesAbility speciesAbility = mock(SpeciesAbility.class);

        SpeciesAbilityInputDto input = new SpeciesAbilityInputDto();
        input.setName(NAME);
        input.setDelete(false);

        when(abilityRepository.findByName(NAME)).thenReturn(ABILITY);
        when(speciesAbilityRepository.findBySpeciesAndAbility(SPECIES, ABILITY)).thenReturn(speciesAbility);

        speciesAbilityService.update(SPECIES, input);

        verify(speciesAbility, times(1)).update(input);
        verify(speciesAbilityRepository, times(1)).save(speciesAbility);
    }

    @Test
    public void createWhenFormDoesNotExist() {
        SpeciesAbilityInputDto input = new SpeciesAbilityInputDto();
        input.setName(NAME);

        when(abilityRepository.findByName(NAME)).thenReturn(ABILITY);

        speciesAbilityService.update(SPECIES, input);

        verify(speciesAbilityRepository, times(1)).save(Matchers.any());
    }
}