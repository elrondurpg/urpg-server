package com.pokemonurpg.stats.service;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.service.AbilityService;
import com.pokemonurpg.stats.input.OwnedHiddenAbilityInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OwnedHiddenAbilityServiceTest {
    private final static Ability ABILITY1 = mock(Ability.class);
    private final static Ability ABILITY2 = mock(Ability.class);
    private final static String ABILITY_NAME1 = "ABILITY_NAME1";
    private final static String ABILITY_NAME2 = "ABILITY_NAME2";

    @InjectMocks
    private OwnedHiddenAbilityService ownedHiddenAbilityService;

    @Mock
    private AbilityService abilityService;

    @Test
    public void updateAll() {
        // Given an OwnedHiddenAbilityInputDto with ability AbilityName and Delete = false;
        OwnedHiddenAbilityInputDto input1 = new OwnedHiddenAbilityInputDto();
        input1.setAbility(ABILITY_NAME1);
        input1.setDelete(false);

        // Given an OwnedHiddenAbilityInputDto with ability AbilityName2 and Delete = true;
        OwnedHiddenAbilityInputDto input2 = new OwnedHiddenAbilityInputDto();
        input2.setAbility(ABILITY_NAME2);
        input2.setDelete(true);

        // Given an OwnedPokemonInputDto that contains those hidden ability inputs
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();
        input.setOwnedHiddenAbilities(Arrays.asList(input1, input2));

        // Given an OwnedPokemon with 'abilitys' containing ability2
        OwnedPokemon ownedPokemon = new OwnedPokemon();
        Set<Ability> abilitys = new HashSet<>();
        abilitys.add(ABILITY2);
        ownedPokemon.setOwnedHiddenAbilities(abilitys);

        // Given an ability service with ABILITY_NAME1 => ABILITY1 and ABILITY_NAME2 => ABILITY2
        when(abilityService.findByName(ABILITY_NAME1)).thenReturn(ABILITY1);
        when(abilityService.findByName(ABILITY_NAME2)).thenReturn(ABILITY2);

        // when I call updateAll
        ownedHiddenAbilityService.updateAll(input, ownedPokemon);

        // Then the pokemon's owned hidden ability set will contain ability1 and not ability2
        assertTrue(ownedPokemon.getOwnedHiddenAbilities().contains(ABILITY1));
        assertFalse(ownedPokemon.getOwnedHiddenAbilities().contains(ABILITY2));

    }

}