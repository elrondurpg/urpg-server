package com.pokemonurpg.configuration.v1.pokemon.species.service;

import com.pokemonurpg.configuration.v1.pokemon.species.input.CosmeticFormInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesRepository;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.creative.models.ArtRank;
import com.pokemonurpg.creative.models.ParkLocation;
import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.models.StoryRank;
import com.pokemonurpg.creative.service.ArtRankService;
import com.pokemonurpg.creative.service.ParkLocationService;
import com.pokemonurpg.creative.service.ParkRankService;
import com.pokemonurpg.creative.service.StoryRankService;

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
public class SpeciesServiceTest {
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
    private final static Species PRE_EVOLUTION = new Species();
    private final static Species PRE_MEGA = new Species();
    private final static String TYPE1_NAME = "TYPE1_NAME";
    private final static String TYPE2_NAME = "TYPE2_NAME";
    private final static String STORY_RANK_NAME = "STORY_RANK_NAME";
    private final static String ART_RANK_NAME = "ART_RANK_NAME";
    private final static String PARK_LOCATION_NAME = "PARK_LOCATION_NAME";
    private final static String PARK_RANK_NAME = "PARK_RANK_NAME";
    private final static String PRE_EVOLUTION_NAME = "PRE_EVOLUTION_NAME";
    private final static String PRE_MEGA_NAME = "PRE_MEGA_NAME";
    private final static Species SPECIES = mock(Species.class);
    private final static List<Species> SPECIES_LIST = new ArrayList<>();

    @InjectMocks
    private SpeciesService speciesService;

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
    private SpeciesAttackService speciesAttackService;

    @Mock
    private SpeciesAbilityService speciesAbilityService;

    @Mock
    private CosmeticFormService cosmeticFormService;

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        //when(speciesRepository.findAllNames()).thenReturn(names);
        //assertEquals(names, speciesService.findAllNames(false));
    }

    @Test
    public void findByDbid() {
        Species species = new Species();
        when(speciesRepository.findByDbid(DBID)).thenReturn(species);
        assertEquals(species, speciesService.findByDbid(DBID));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(speciesRepository.findByName(NAME)).thenReturn(null);
        when(speciesRepository.findFirstByNameStartingWith(NAME)).thenReturn(SPECIES);
        assertEquals(SPECIES, speciesService.findByName(NAME));
    }

    @Test
    public void create() {
        SpeciesInputDto input = new SpeciesInputDto();
        input.setName(NAME);

        Species species = speciesService.create(input);
        assertEquals(NAME, species.getName());
        verify(speciesRepository, times(1)).save(species);
    }

    @Test
    public void updateReturnsNullWhenSpeciesDoesntExist() {
        SpeciesInputDto input = new SpeciesInputDto();
        input.setName(NAME);

        when(speciesRepository.findByDbid(DBID)).thenReturn(null);

        Species species = speciesService.update(input, DBID);
        assertNull(species);
        verify(speciesRepository, times(0)).save(Matchers.any());
    }

    @Test
    public void updateExistingSpecies() {
        SpeciesInputDto input = new SpeciesInputDto();
        input.setName(NAME);

        Species species = new Species();

        when(speciesRepository.findByDbid(DBID)).thenReturn(species);

        Species result = speciesService.update(input, DBID);
        assertEquals(NAME, result.getName());
        verify(speciesRepository, times(1)).save(Matchers.any());
    }

    @Test
    public void updateEmbeddedValues() {
        Species species = new Species();

        SpeciesInputDto input = new SpeciesInputDto();
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

        speciesService.updateEmbeddedValues(species, input);
        assertEquals(TYPE1, species.getType1());
        assertEquals(TYPE2, species.getType2());
        assertEquals(STORY_RANK, species.getStoryRank());
        assertEquals(ART_RANK, species.getArtRank());
        assertEquals(PARK_LOCATION, species.getParkLocation());
        assertEquals(PARK_RANK, species.getParkRank());
        assertEquals(PRE_EVOLUTION, species.getPreEvolution());
        assertEquals(PRE_MEGA, species.getPreMega());
    }

    @Test
    public void updateAssociatedValues() {
        Species species = new Species();
        SpeciesInputDto input = new SpeciesInputDto();

        SpeciesAttackInputDto attack1 = new SpeciesAttackInputDto();
        SpeciesAttackInputDto attack2 = new SpeciesAttackInputDto();
        List<SpeciesAttackInputDto> attacks = Arrays.asList(attack1, attack2);
        input.setAttacks(attacks);

        SpeciesAbilityInputDto ability1 = new SpeciesAbilityInputDto();
        SpeciesAbilityInputDto ability2 = new SpeciesAbilityInputDto();
        List<SpeciesAbilityInputDto> abilities = Arrays.asList(ability1, ability2);
        input.setAbilities(abilities);

        CosmeticFormInputDto form1 = new CosmeticFormInputDto();
        CosmeticFormInputDto form2 = new CosmeticFormInputDto();
        List<CosmeticFormInputDto> forms = Arrays.asList(form1, form2);
        input.setCosmeticForms(forms);

        speciesService.updateAssociatedValues(species, input);
        verify(speciesAttackService, times(1)).update(species, attack1);
        verify(speciesAttackService, times(1)).update(species, attack2);
        verify(speciesAbilityService, times(1)).update(species, ability1);
        verify(speciesAbilityService, times(1)).update(species, ability2);
        verify(cosmeticFormService, times(1)).update(form1, DBID);
        verify(cosmeticFormService, times(1)).update(form2, DBID);
    }
}