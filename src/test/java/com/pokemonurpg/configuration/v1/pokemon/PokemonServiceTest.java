package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.entities.v1.ArtRank;
import com.pokemonurpg.entities.v1.ParkLocation;
import com.pokemonurpg.entities.v1.ParkRank;
import com.pokemonurpg.entities.v1.StoryRank;
import com.pokemonurpg.configuration.v1.artranks.ArtRankService;
import com.pokemonurpg.configuration.v1.parklocations.ParkLocationService;
import com.pokemonurpg.configuration.v1.parkranks.ParkRankService;
import com.pokemonurpg.configuration.v1.storyranks.StoryRankService;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.Type;
import com.pokemonurpg.infrastructure.v1.data.jpa.SpeciesRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PokemonServiceTest {
    private final static Integer DBID = 2342;
    private final static String NAME = "NAME";
    private final static Type TYPE1 = new Type();
    private final static Type TYPE2 = new Type();
    private final static Integer DEXNO = 2421;
    private final static Integer MAX_DEXNO = 32421;
    private final static StoryRank STORY_RANK = new StoryRank();
    private final static ArtRank ART_RANK = new ArtRank();
    private final static ParkLocation PARK_LOCATION = new ParkLocation();
    private final static ParkRank PARK_RANK = new ParkRank();
    private final static Pokemon PRE_EVOLUTION = new Pokemon();
    private final static Pokemon PRE_MEGA = new Pokemon();
    private final static String TYPE1_NAME = "TYPE1_NAME";
    private final static String TYPE2_NAME = "TYPE2_NAME";
    private final static String STORY_RANK_NAME = "STORY_RANK_NAME";
    private final static String ART_RANK_NAME = "ART_RANK_NAME";
    private final static String PARK_LOCATION_NAME = "PARK_LOCATION_NAME";
    private final static String PARK_RANK_NAME = "PARK_RANK_NAME";
    private final static String PRE_EVOLUTION_NAME = "PRE_EVOLUTION_NAME";
    private final static String PRE_MEGA_NAME = "PRE_MEGA_NAME";
    private final static Pokemon POKEMON = mock(Pokemon.class);
    private final static List<Pokemon> POKEMON_LIST = new ArrayList<>();

    @InjectMocks
    private PokemonService pokemonService;

    @Mock
    private SpeciesRepository speciesRepository;

    @Mock
    private TypeService typeService;

    @Mock
    private StoryRankService storyRankService;

    @Mock
    private ArtRankService artRankService;

    @Mock
    private ParkRankService parkRankService;

    @Mock
    private ParkLocationService parkLocationService;

    @Mock
    private PokemonAttackService pokemonAttackService;

    @Mock
    private PokemonAbilityService pokemonAbilityService;

    @Mock
    private CosmeticFormService cosmeticFormService;

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(speciesRepository.findAllNames()).thenReturn(names);
        assertEquals(names, pokemonService.findAllNames(false));
    }

    @Test
    public void findByDbid() {
        Pokemon pokemon = new Pokemon();
        when(speciesRepository.findByDbid(DBID)).thenReturn(pokemon);
        assertEquals(pokemon, pokemonService.findByDbid(DBID));
    }

    @Test
    public void findByDexno() {
        when(speciesRepository.findByDexno(DEXNO)).thenReturn(POKEMON_LIST);
        assertEquals(POKEMON_LIST, pokemonService.findByDexno(DEXNO));
    }

    @Test
    public void findFirstByDexno() {
        when(speciesRepository.findFirstByDexno(DEXNO)).thenReturn(POKEMON);
        assertEquals(POKEMON, pokemonService.findFirstByDexno(DEXNO));
    }

    @Test
    public void findMaxDexno() {
        when(speciesRepository.findMaxDexno()).thenReturn(MAX_DEXNO);
        assertEquals(MAX_DEXNO, pokemonService.findMaxDexno());
    }

    @Test
    public void findByNameNotExactMatch() {
        when(speciesRepository.findByName(NAME)).thenReturn(null);
        when(speciesRepository.findFirstByNameStartingWith(NAME)).thenReturn(POKEMON);
        assertEquals(POKEMON, pokemonService.findByName(NAME));
    }

    @Test
    public void findByPreEvolution() {
        when(speciesRepository.findByPreEvolution(PRE_EVOLUTION)).thenReturn(POKEMON_LIST);
        assertEquals(POKEMON_LIST, pokemonService.findByPreEvolution(PRE_EVOLUTION));
    }

    @Test
    public void findByPreMega() {
        when(speciesRepository.findByPreMega(PRE_EVOLUTION)).thenReturn(POKEMON_LIST);
        assertEquals(POKEMON_LIST, pokemonService.findByPreMega(PRE_EVOLUTION));
    }

    @Test
    public void create() {
        PokemonRequest input = new PokemonRequest();
        input.setName(NAME);

        Pokemon pokemon = pokemonService.create(input);
        assertEquals(NAME, pokemon.getName());
        verify(speciesRepository, times(1)).save(pokemon);
    }

    @Ignore("Replace speciesRepository with Fake-style test double in order to avoid verify call")
    @Test
    public void updateReturnsNullWhenSpeciesDoesntExist() {
        PokemonRequest input = new PokemonRequest();
        input.setName(NAME);

        when(speciesRepository.findByDbid(DBID)).thenReturn(null);

        Pokemon pokemon = pokemonService.update(input, DBID);
        assertNull(pokemon);
        verify(speciesRepository, times(0)).save(Matchers.any());
    }

    @Ignore("Replace speciesRepository with Fake-style test double in order to avoid verify call")
    @Test
    public void updateExistingSpecies() {
        PokemonRequest input = new PokemonRequest();
        input.setName(NAME);

        Pokemon pokemon = new Pokemon();

        when(speciesRepository.findByDbid(DBID)).thenReturn(pokemon);

        Pokemon result = pokemonService.update(input, DBID);
        assertEquals(NAME, result.getName());
        verify(speciesRepository, times(1)).save(Matchers.any());
    }

    @Test
    public void updateEmbeddedValues() {
        Pokemon pokemon = new Pokemon();

        PokemonRequest input = new PokemonRequest();
        input.setType1(TYPE1_NAME);
        input.setType2(TYPE2_NAME);
        input.setStoryRank(STORY_RANK_NAME);
        input.setParkLocation(PARK_LOCATION_NAME);
        input.setParkRank(PARK_RANK_NAME);
        input.setArtRank(ART_RANK_NAME);
        input.setPreEvolution(PRE_EVOLUTION_NAME);
        input.setPreMega(PRE_MEGA_NAME);

        when(typeService.findByName(TYPE1_NAME)).thenReturn(TYPE1);
        when(typeService.findByName(TYPE2_NAME)).thenReturn(TYPE2);
        when(storyRankService.findByName(STORY_RANK_NAME)).thenReturn(STORY_RANK);
        when(parkLocationService.findByName(PARK_LOCATION_NAME)).thenReturn(PARK_LOCATION);
        when(parkRankService.findByName(PARK_RANK_NAME)).thenReturn(PARK_RANK);
        when(artRankService.findByName(ART_RANK_NAME)).thenReturn(ART_RANK);
        when(speciesRepository.findByName(PRE_EVOLUTION_NAME)).thenReturn(PRE_EVOLUTION);
        when(speciesRepository.findByName(PRE_MEGA_NAME)).thenReturn(PRE_MEGA);

        pokemonService.updateEmbeddedValues(pokemon, input);
        assertEquals(TYPE1, pokemon.getType1());
        assertEquals(TYPE2, pokemon.getType2());
        assertEquals(STORY_RANK, pokemon.getStoryRank());
        assertEquals(ART_RANK, pokemon.getArtRank());
        assertEquals(PARK_LOCATION, pokemon.getParkLocation());
        assertEquals(PARK_RANK, pokemon.getParkRank());
        assertEquals(PRE_EVOLUTION, pokemon.getPreEvolution());
        assertEquals(PRE_MEGA, pokemon.getPreMega());
    }

    @Ignore("Replace repositories with Fake-style test double in order to avoid verify call")
    @Test
    public void updateAssociatedValues() {
        Pokemon pokemon = new Pokemon();
        PokemonRequest input = new PokemonRequest();

        PokemonAttackRequest attack1 = new PokemonAttackRequest();
        PokemonAttackRequest attack2 = new PokemonAttackRequest();
        List<PokemonAttackRequest> attacks = Arrays.asList(attack1, attack2);
        input.setAttacks(attacks);

        PokemonAbilityRequest ability1 = new PokemonAbilityRequest();
        PokemonAbilityRequest ability2 = new PokemonAbilityRequest();
        List<PokemonAbilityRequest> abilities = Arrays.asList(ability1, ability2);
        input.setAbilities(abilities);

        CosmeticFormRequest form1 = new CosmeticFormRequest();
        CosmeticFormRequest form2 = new CosmeticFormRequest();
        List<CosmeticFormRequest> forms = Arrays.asList(form1, form2);
        input.setCosmeticForms(forms);

        pokemonService.updateAssociatedValues(pokemon, input);
        verify(pokemonAttackService, times(1)).update(pokemon, attack1);
        verify(pokemonAttackService, times(1)).update(pokemon, attack2);
        verify(pokemonAbilityService, times(1)).update(pokemon, ability1);
        verify(pokemonAbilityService, times(1)).update(pokemon, ability2);
        verify(cosmeticFormService, times(1)).update(form1, DBID);
        verify(cosmeticFormService, times(1)).update(form2, DBID);
    }
}