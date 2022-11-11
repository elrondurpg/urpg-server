package com.pokemonurpg.configuration.v1.pokemon.ability.service;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.input.AbilityInputTestDto;
import com.pokemonurpg.configuration.v1.pokemon.ability.repository.AbilityRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesAbilityService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbilityServiceTest {

    @InjectMocks
    private AbilityService service;

    @Mock
    private AbilityRepository abilityRepository;

    @Mock
    private SpeciesAbilityService speciesAbilityService;

    @Captor
    ArgumentCaptor<SpeciesAbility> abilityCaptor;

    @Test
    public void test_createBase() {
        AbilityInputTestDto input = new AbilityInputTestDto();
        Ability ability = service.createBase(input);
        assertNotNull(ability);
    }

    @Test
    public void test_updateBase() {
        AbilityInputTestDto input = new AbilityInputTestDto();
        Ability ability = new Ability();
        service.updateBase(ability, input);
        assertNotNull(ability);
    }


    public void assert_UpdateBase_Valid(Ability ability, AbilityInputTestDto input) {
        assertEquals(input.getName(), ability.getName());
        assertEquals(input.getDescription(), ability.getDescription());
    }

    @Test
    public void test_updateEmbeddedValues() {
        AbilityInputTestDto input = new AbilityInputTestDto();
        Ability ability = new Ability();
        service.updateEmbeddedValues(ability, input);
    }

    @Test
    public void test_updateAssociatedValues() {
        AbilityInputTestDto input = new AbilityInputTestDto();
        Ability ability = new Ability();
        service.updateAssociatedValues(ability, input);
    }
    
    private Ability setup_deleteAssociatedValues() {
        Ability ability = new Ability();
        SpeciesAbility speciesAbility = new SpeciesAbility();
        ability.setPokemon(Collections.singleton(speciesAbility));
        return ability;
    }

    @Test
    public void test_deleteAssociatedValues() {
        Ability ability = setup_deleteAssociatedValues();
        service.deleteAssociatedValues(ability);
        assert_deleteAssociatedValues_Valid(ability);
    }

    private void assert_deleteAssociatedValues_Valid(Ability ability) {
        ability.getPokemon().forEach(pokemon -> {
            verify(speciesAbilityService, times(1)).delete(abilityCaptor.capture());
            assertEquals(pokemon, abilityCaptor.getValue());
        });
    }
}