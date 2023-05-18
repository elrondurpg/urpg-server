package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
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
    private final static Pokemon POKEMON = new Pokemon();
    private final static String SPECIES_NAME = "SPECIES";
    private final static Pokemon PREV_DEX = new Pokemon();
    private final static Pokemon NEXT_DEX = new Pokemon();
    private final static List<AlteredFormResponse> ALTERED_FORMS = new ArrayList<>();
    private final static AlteredFormResponse ALTERED_FORM = mock(AlteredFormResponse.class);
    private final static Map<String, String> ATTACKS_THAT_DIFFER_BY_FORM = new HashMap<>();
    private final static String ATTACK_THAT_DIFFERS_BY_FORM = "ATTACK";
    private final static String METHOD = "METHOD";
    private final static List<List<Pokemon>> EVOLUTION_FAMILY = new ArrayList<>();
    private final static List<MegaEvolutionResponse> MEGA_EVOLUTIONS = new ArrayList<>();
    private final static List<TypeMatchupResponse> TYPE_MATCHUPS = new ArrayList<>();

    @InjectMocks
    private PokedexService pokedexService;

    @Mock
    private PokemonService pokemonService;

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
        Pokemon preMega = new Pokemon();
        Pokemon mega = new Pokemon();
        mega.setPreMega(preMega);
        assertEquals(preMega, pokedexService.switchToPreMegaIfNeeded(mega));
    }

    @Test
    public void dontSwitchToPreMega() {
        Pokemon preMega = new Pokemon();
        Pokemon mega = new Pokemon();
        mega.setPreMega(preMega);
        assertEquals(preMega, pokedexService.switchToPreMegaIfNeeded(preMega));
    }

    @Test
    public void testSpeciesNotFound() {
        when(pokemonService.findByName(SPECIES_NAME)).thenReturn(null);
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
        when(pokemonService.findByName(SPECIES_NAME)).thenReturn(POKEMON);
        when(speciesPageTabService.findPrevDexBySpecies(POKEMON)).thenReturn(PREV_DEX);
        when(speciesPageTabService.findNextDexBySpecies(POKEMON)).thenReturn(NEXT_DEX);
        when(alteredFormService.findBySpecies(POKEMON)).thenReturn(ALTERED_FORMS);
        when(evolutionFamilyService.findBySpecies(POKEMON)).thenReturn(EVOLUTION_FAMILY);
        when(megaEvolutionService.findBySpecies(POKEMON)).thenReturn(MEGA_EVOLUTIONS);
        when(typeMatchupService.findBySpecies(POKEMON)).thenReturn(TYPE_MATCHUPS);

        PokemonResponse entry = pokedexService.findByName(SPECIES_NAME);
        assertEquals(POKEMON, entry.getSpecies());
        assertEquals(PREV_DEX, entry.getPrevDex());
        assertEquals(NEXT_DEX, entry.getNextDex());
        assertEquals(ALTERED_FORMS, entry.getAlteredForms());
        assertEquals(ATTACKS_THAT_DIFFER_BY_FORM.keySet().size(), entry.getAttacksThatDifferByForm().size());
        assertEquals(EVOLUTION_FAMILY, entry.getEvolutionFamily());
        assertEquals(MEGA_EVOLUTIONS, entry.getMegaEvolutions());
        assertEquals(TYPE_MATCHUPS, entry.getTypeMatchups());
    }
}