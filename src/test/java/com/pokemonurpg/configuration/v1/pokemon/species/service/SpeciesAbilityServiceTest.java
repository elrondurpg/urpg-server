package com.pokemonurpg.configuration.v1.pokemon.species.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.repository.AbilityRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesAbilityRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

        verify(speciesAbilityRepository, times(1)).save(ArgumentMatchers.any());
    }
}