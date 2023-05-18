package com.pokemonurpg.pokedex.util;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.pokedex.v1.AlteredFormDto;
import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.entities.v1.SpeciesAttack;
import com.pokemonurpg.pokedex.v1.FormAttackSorter;
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
        Species species1 = mock(Species.class);
        Species species2 = mock(Species.class);
        Species species3 = mock(Species.class);

        // Given each Species has a SpeciesAttack that maps attack X with method Y
        SpeciesAttack speciesAttack = mock(SpeciesAttack.class);
        when(ATTACK.getName()).thenReturn(ATTACK_NAME);
        when(speciesAttack.getAttack()).thenReturn(ATTACK);
        when(speciesAttack.getMethod()).thenReturn(METHOD1);
        when(species1.getAttacks()).thenReturn(Collections.singletonList(speciesAttack));
        when(species2.getAttacks()).thenReturn(Collections.singletonList(speciesAttack));
        when(species3.getAttacks()).thenReturn(Collections.singletonList(speciesAttack));

        // Given a set of alteredForms for each Species
        AlteredFormDto form1 = new AlteredFormDto(species1);
        AlteredFormDto form2 = new AlteredFormDto(species2);
        AlteredFormDto form3 = new AlteredFormDto(species3);
        List<AlteredFormDto> alteredForms = Arrays.asList(form1, form2, form3);

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
        Species species1 = mock(Species.class);
        Species species2 = mock(Species.class);
        Species species3 = mock(Species.class);

        SpeciesAttack speciesAttack = mock(SpeciesAttack.class);
        when(ATTACK.getName()).thenReturn(ATTACK_NAME);
        when(speciesAttack.getAttack()).thenReturn(ATTACK);
        when(speciesAttack.getMethod()).thenReturn(METHOD1);

        SpeciesAttack speciesAttack2 = mock(SpeciesAttack.class);
        when(speciesAttack2.getAttack()).thenReturn(ATTACK);
        when(speciesAttack2.getMethod()).thenReturn(METHOD2);

        when(species1.getAttacks()).thenReturn(Collections.singletonList(speciesAttack));
        when(species2.getAttacks()).thenReturn(Collections.singletonList(speciesAttack2));
        when(species3.getAttacks()).thenReturn(Collections.singletonList(speciesAttack));

        // Given a set of alteredForms for each Species
        AlteredFormDto form1 = new AlteredFormDto(species1);
        AlteredFormDto form2 = new AlteredFormDto(species2);
        AlteredFormDto form3 = new AlteredFormDto(species3);
        List<AlteredFormDto> alteredForms = Arrays.asList(form1, form2, form3);

        // When I call formAttackSorter.run(alteredForms)
        formAttackSorter.run(alteredForms);

        // Then for each alteredForm,
        // Then that form's 'attacksThatDifferByForm' will not contain that attack
        alteredForms.forEach(alteredForm -> {
            assertTrue(alteredForm.getAttacksThatDifferByForm().containsKey(ATTACK_NAME));
        });
    }

}