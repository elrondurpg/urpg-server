package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.infrastructure.v1.data.jpa.AbilityRepository;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.PokemonAbility;
import com.pokemonurpg.infrastructure.v1.data.jpa.PokemonAbilityRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PokemonAbilityServiceTest {
    private final static String NAME = "NAME";
    private final static Ability ABILITY = new Ability();
    private final static Pokemon POKEMON = new Pokemon();

    @InjectMocks
    private PokemonAbilityService pokemonAbilityService;
    
    @Mock
    private PokemonAbilityRepository pokemonAbilityRepository;

    @Mock
    private AbilityRepository abilityRepository;

    @Test
    public void deleteWhenFormExistsAndDeleteIsTrue() {
        PokemonAbility pokemonAbility = mock(PokemonAbility.class);

        PokemonAbilityRequest input = new PokemonAbilityRequest();
        input.setName(NAME);
        input.setDelete(true);

        when(abilityRepository.findByName(NAME)).thenReturn(ABILITY);
        when(pokemonAbilityRepository.findBySpeciesAndAbility(POKEMON, ABILITY)).thenReturn(pokemonAbility);

        pokemonAbilityService.update(POKEMON, input);

        verify(pokemonAbilityRepository, times(1)).delete(pokemonAbility);
    }

    @Test
    public void updateWhenFormExistsAndDeleteIsFalse() {
        PokemonAbility pokemonAbility = mock(PokemonAbility.class);

        PokemonAbilityRequest input = new PokemonAbilityRequest();
        input.setName(NAME);
        input.setDelete(false);

        when(abilityRepository.findByName(NAME)).thenReturn(ABILITY);
        when(pokemonAbilityRepository.findBySpeciesAndAbility(POKEMON, ABILITY)).thenReturn(pokemonAbility);

        pokemonAbilityService.update(POKEMON, input);

        verify(pokemonAbility, times(1)).update(input);
        verify(pokemonAbilityRepository, times(1)).save(pokemonAbility);
    }

    @Ignore("Replace speciesAbilityRepository with Fake-style test double in order to avoid verify call")
    @Test
    public void createWhenFormDoesNotExist() {
        PokemonAbilityRequest input = new PokemonAbilityRequest();
        input.setName(NAME);

        when(abilityRepository.findByName(NAME)).thenReturn(ABILITY);

        pokemonAbilityService.update(POKEMON, input);

        verify(pokemonAbilityRepository, times(1)).save(Matchers.any());
    }
}