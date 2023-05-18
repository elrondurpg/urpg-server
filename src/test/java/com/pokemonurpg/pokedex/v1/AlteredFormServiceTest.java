package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlteredFormServiceTest {
    private final static Integer DEXNO = 3242;

    @InjectMocks
    private AlteredFormService alteredFormService;

    @Mock
    private PokemonService pokemonService;

    @Mock
    private FormAttackSorter formAttackSorter;

    @Test
    public void returnsEmptyListWhenSpeciesHasNoAlternateForms() {
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setDexno(DEXNO);
        when(pokemonService.findByDexno(DEXNO)).thenReturn(Collections.singletonList(pokemon1));

        List<AlteredFormResponse> alteredForms = alteredFormService.findBySpecies(pokemon1);
        assertEquals(0, alteredForms.size());
    }

    @Test
    public void returnsListOfAlteredForms() {
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setDexno(DEXNO);
        Pokemon pokemon2 = new Pokemon();
        pokemon2.setDexno(DEXNO);
        when(pokemonService.findByDexno(DEXNO)).thenReturn(Arrays.asList(pokemon1, pokemon2));

        List<AlteredFormResponse> alteredForms = alteredFormService.findBySpecies(pokemon1);
        verify(formAttackSorter, times(1)).run(alteredForms);
        assertEquals(2, alteredForms.size());
    }

    @Test
    public void returnsEmptyListWhenOneOfTwoFormsIsAMega() {
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setDexno(DEXNO);
        Pokemon pokemon2 = new Pokemon();
        pokemon2.setDexno(DEXNO);
        pokemon2.setPreMega(pokemon1);
        when(pokemonService.findByDexno(DEXNO)).thenReturn(Arrays.asList(pokemon1, pokemon2));

        List<AlteredFormResponse> alteredForms = alteredFormService.findBySpecies(pokemon1);
        assertEquals(0, alteredForms.size());
    }
}