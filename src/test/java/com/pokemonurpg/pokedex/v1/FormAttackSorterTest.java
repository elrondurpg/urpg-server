package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.PokemonAttack;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FormAttackSorterTest {
    private final static String ATTACK_NAME = "ATTACK_NAME";
    private final static Attack ATTACK = mock(Attack.class);
    private final static String METHOD1 = "METHOD";
    private final static String METHOD2 = "METHOD2";

    private FormAttackSorter formAttackSorter = new FormAttackSorter();

    @Test
    public void alteredFormsDontMapAttackWhenAllMethodsAreEqual() {
        // Given a set of Species
        Pokemon pokemon1 = mock(Pokemon.class);
        Pokemon pokemon2 = mock(Pokemon.class);
        Pokemon pokemon3 = mock(Pokemon.class);

        // Given each Species has a SpeciesAttack that maps attack X with method Y
        PokemonAttack pokemonAttack = mock(PokemonAttack.class);
        when(ATTACK.getName()).thenReturn(ATTACK_NAME);
        when(pokemonAttack.getAttack()).thenReturn(ATTACK);
        when(pokemonAttack.getMethod()).thenReturn(METHOD1);
        when(pokemon1.getAttacks()).thenReturn(Collections.singletonList(pokemonAttack));
        when(pokemon2.getAttacks()).thenReturn(Collections.singletonList(pokemonAttack));
        when(pokemon3.getAttacks()).thenReturn(Collections.singletonList(pokemonAttack));

        // Given a set of alteredForms for each Species
        AlteredFormResponse form1 = new AlteredFormResponse(pokemon1);
        AlteredFormResponse form2 = new AlteredFormResponse(pokemon2);
        AlteredFormResponse form3 = new AlteredFormResponse(pokemon3);
        List<AlteredFormResponse> alteredForms = Arrays.asList(form1, form2, form3);

        // When I call formAttackSorter.run(alteredForms)
        formAttackSorter.run(alteredForms);

        // Then for each alteredForm,
            // Then that form's 'attacksThatDifferByForm' will not contain that attack
        alteredForms.forEach(alteredForm -> {
           assertFalse(alteredForm.getAttacksThatDifferByForm().containsKey(ATTACK_NAME));
        });
    }

    @Test
    public void alteredFormsMapAttackWhenMethodsAreDifferent() {
        // Given a set of Species
        Pokemon pokemon1 = mock(Pokemon.class);
        Pokemon pokemon2 = mock(Pokemon.class);
        Pokemon pokemon3 = mock(Pokemon.class);

        PokemonAttack pokemonAttack = mock(PokemonAttack.class);
        when(ATTACK.getName()).thenReturn(ATTACK_NAME);
        when(pokemonAttack.getAttack()).thenReturn(ATTACK);
        when(pokemonAttack.getMethod()).thenReturn(METHOD1);

        PokemonAttack pokemonAttack2 = mock(PokemonAttack.class);
        when(pokemonAttack2.getAttack()).thenReturn(ATTACK);
        when(pokemonAttack2.getMethod()).thenReturn(METHOD2);

        when(pokemon1.getAttacks()).thenReturn(Collections.singletonList(pokemonAttack));
        when(pokemon2.getAttacks()).thenReturn(Collections.singletonList(pokemonAttack2));
        when(pokemon3.getAttacks()).thenReturn(Collections.singletonList(pokemonAttack));

        // Given a set of alteredForms for each Species
        AlteredFormResponse form1 = new AlteredFormResponse(pokemon1);
        AlteredFormResponse form2 = new AlteredFormResponse(pokemon2);
        AlteredFormResponse form3 = new AlteredFormResponse(pokemon3);
        List<AlteredFormResponse> alteredForms = Arrays.asList(form1, form2, form3);

        // When I call formAttackSorter.run(alteredForms)
        formAttackSorter.run(alteredForms);

        // Then for each alteredForm,
        // Then that form's 'attacksThatDifferByForm' will not contain that attack
        alteredForms.forEach(alteredForm -> {
            assertTrue(alteredForm.getAttacksThatDifferByForm().containsKey(ATTACK_NAME));
        });
    }

}