package com.pokemonurpg.pokedex.service;

import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesService;
import com.pokemonurpg.pokedex.v1.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokedexServiceTest {
    private final static Species SPECIES = new Species();
    private final static String SPECIES_NAME = "SPECIES";
    private final static Species PREV_DEX = new Species();
    private final static Species NEXT_DEX = new Species();
    private final static List<AlteredFormDto> ALTERED_FORMS = new ArrayList<>();
    private final static AlteredFormDto ALTERED_FORM = mock(AlteredFormDto.class);
    private final static Map<String, String> ATTACKS_THAT_DIFFER_BY_FORM = new HashMap<>();
    private final static String ATTACK_THAT_DIFFERS_BY_FORM = "ATTACK";
    private final static String METHOD = "METHOD";
    private final static List<List<Species>> EVOLUTION_FAMILY = new ArrayList<>();
    private final static List<MegaEvolutionDto> MEGA_EVOLUTIONS = new ArrayList<>();
    private final static List<TypeMatchupDto> TYPE_MATCHUPS = new ArrayList<>();

    @InjectMocks
    private PokedexService pokedexService;

    @Mock
    private SpeciesService speciesService;

    @Mock
    private SpeciesPageTabService speciesPageTabService;

    @Mock
    private AlteredFormService alteredFormService;

    @Mock
    private EvolutionFamilyService evolutionFamilyService;

    @Mock
    private MegaEvolutionService megaEvolutionService;

    @Mock
    private TypeMatchupService typeMatchupService;

    @Test
    public void switchToPreMega() {
        Species preMega = new Species();
        Species mega = new Species();
        mega.setPreMega(preMega);
        assertEquals(preMega, pokedexService.switchToPreMegaIfNeeded(mega));
    }

    @Test
    public void dontSwitchToPreMega() {
        Species preMega = new Species();
        Species mega = new Species();
        mega.setPreMega(preMega);
        assertEquals(preMega, pokedexService.switchToPreMegaIfNeeded(preMega));
    }

    @Test
    public void testSpeciesNotFound() {
        when(speciesService.findByName(SPECIES_NAME)).thenReturn(null);
        assertNull(pokedexService.findByName(SPECIES_NAME));
    }

    @Before
    public void setUpAlteredFormAttacks() {
        ALTERED_FORMS.add(ALTERED_FORM);
        when(ALTERED_FORM.getAttacksThatDifferByForm()).thenReturn(ATTACKS_THAT_DIFFER_BY_FORM);
        ATTACKS_THAT_DIFFER_BY_FORM.put(ATTACK_THAT_DIFFERS_BY_FORM, METHOD);
    }

    @Test
    public void testFindByName() {
        when(speciesService.findByName(SPECIES_NAME)).thenReturn(SPECIES);
        when(speciesPageTabService.findPrevDexBySpecies(SPECIES)).thenReturn(PREV_DEX);
        when(speciesPageTabService.findNextDexBySpecies(SPECIES)).thenReturn(NEXT_DEX);
        when(alteredFormService.findBySpecies(SPECIES)).thenReturn(ALTERED_FORMS);
        when(evolutionFamilyService.findBySpecies(SPECIES)).thenReturn(EVOLUTION_FAMILY);
        when(megaEvolutionService.findBySpecies(SPECIES)).thenReturn(MEGA_EVOLUTIONS);
        when(typeMatchupService.findBySpecies(SPECIES)).thenReturn(TYPE_MATCHUPS);

        PokedexEntryDto entry = pokedexService.findByName(SPECIES_NAME);
        assertEquals(SPECIES, entry.getSpecies());
        assertEquals(PREV_DEX, entry.getPrevDex());
        assertEquals(NEXT_DEX, entry.getNextDex());
        assertEquals(ALTERED_FORMS, entry.getAlteredForms());
        assertEquals(ATTACKS_THAT_DIFFER_BY_FORM.keySet().size(), entry.getAttacksThatDifferByForm().size());
        assertEquals(EVOLUTION_FAMILY, entry.getEvolutionFamily());
        assertEquals(MEGA_EVOLUTIONS, entry.getMegaEvolutions());
        assertEquals(TYPE_MATCHUPS, entry.getTypeMatchups());
    }
}