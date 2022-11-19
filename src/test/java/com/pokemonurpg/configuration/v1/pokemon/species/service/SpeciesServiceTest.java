package com.pokemonurpg.configuration.v1.pokemon.species.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.creative.artrank.service.ArtRankService;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputTestDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.CosmeticForm;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAttack;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesRepository;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.configuration.v1.creative.parklocation.service.ParkLocationService;
import com.pokemonurpg.configuration.v1.creative.parkrank.service.ParkRankService;
import com.pokemonurpg.configuration.v1.creative.storyrank.service.StoryRankService;
import com.pokemonurpg.test.RandomNumberGenerator;

@ExtendWith(MockitoExtension.class)
public class SpeciesServiceTest {
    private static Integer DBID = RandomNumberGenerator.generate();

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
    public void test_updateBase() {
        SpeciesInputTestDto input = new SpeciesInputTestDto();
        Species species = new Species();
        speciesService.updateBase(species, input);
        assertUpdateBaseValid(species, input);
    }


    public void assertUpdateBaseValid(Species species, SpeciesInputDto input) {
        assertEquals(input.getHeight(), species.getHeight());
        assertEquals(input.getWeight(), species.getWeight());
        assertEquals(input.getMaleAllowed(), species.getMaleAllowed());
        assertEquals(input.getFemaleAllowed(), species.getFemaleAllowed());
        assertEquals(input.getPokemart(), species.getPokemart());
        assertEquals(input.getContestCredits(), species.getContestCredits());
        assertEquals(input.getDisplayName(), species.getDisplayName());
        assertEquals(input.getFormName(), species.getFormName());
        assertEquals(input.getName(), species.getName());
        assertEquals(input.getHp(), species.getHp());
        assertEquals(input.getAttack(), species.getAttack());
        assertEquals(input.getDefense(), species.getDefense());
        assertEquals(input.getSpecialAttack(), species.getSpecialAttack());
        assertEquals(input.getSpecialDefense(), species.getSpecialDefense());
        assertEquals(input.getSpeed(), species.getSpeed());
        assertEquals(input.getDexno(), species.getDexno());
        assertEquals(input.getClassification(), species.getClassification());
        assertEquals(input.getLegendaryTier(), species.getLegendaryTier());
        assertEquals(input.getAlteredFormMethod(), species.getAlteredFormMethod());
        assertEquals(input.getEvolutionMethod(), species.getEvolutionMethod());
        assertEquals(input.getEvolutionExpRequirement(), species.getEvolutionExpRequirement());
        assertEquals(input.getMegaStone(), species.getMegaStone());
        assertEquals(input.getMegaSuffix(), species.getMegaSuffix());
        assertEquals(input.getBattleOnly(), species.getBattleOnly());
    }

    private void setupUpdateEmbeddedValues(SpeciesInputTestDto input) {
        when(typeService.findByName(input.getType1())).thenReturn(SpeciesInputTestDto.TYPE1);
        when(typeService.findByName(input.getType2())).thenReturn(SpeciesInputTestDto.TYPE2);
        when(storyRankService.findByName(input.getStoryRank())).thenReturn(SpeciesInputTestDto.STORY_RANK);
        when(artRankService.findByName(input.getArtRank())).thenReturn(SpeciesInputTestDto.ART_RANK);
        when(parkRankService.findByName(input.getParkRank())).thenReturn(SpeciesInputTestDto.PARK_RANK);
        when(parkLocationService.findByName(input.getParkLocation())).thenReturn(SpeciesInputTestDto.PARK_LOCATION);
        when(speciesRepository.findByName(input.getPreEvolution())).thenReturn(SpeciesInputTestDto.PRE_EVOLUTION);
        when(speciesRepository.findByName(input.getPreMega())).thenReturn(SpeciesInputTestDto.PRE_MEGA);
    }

    @Test
    public void test_updateEmbeddedValues() {
        SpeciesInputTestDto input = new SpeciesInputTestDto();
        setupUpdateEmbeddedValues(input);
        Species species = new Species();
        speciesService.updateEmbeddedValues(species, input);
        assertUpdateEmbeddedValuesValid(species, input);
    }

    private void assertUpdateEmbeddedValuesValid(Species species, SpeciesInputDto input) {
        assertEquals(SpeciesInputTestDto.TYPE1, species.getType1());
        assertEquals(SpeciesInputTestDto.TYPE2, species.getType2());
        assertEquals(SpeciesInputTestDto.STORY_RANK, species.getStoryRank());
        assertEquals(SpeciesInputTestDto.ART_RANK, species.getArtRank());
        assertEquals(SpeciesInputTestDto.PARK_LOCATION, species.getParkLocation());
        assertEquals(SpeciesInputTestDto.PARK_RANK, species.getParkRank());
        assertEquals(SpeciesInputTestDto.PRE_EVOLUTION, species.getPreEvolution());
        assertEquals(SpeciesInputTestDto.PRE_MEGA, species.getPreMega());
    }

    private void setupUpdateAssociatedValues(Species species, SpeciesInputDto input) {
        when(speciesAttackService.findBySpecies(species)).thenReturn(SpeciesInputTestDto.ATTACKS);
        when(speciesAbilityService.findBySpecies(species)).thenReturn(SpeciesInputTestDto.ABILITIES);
        when(cosmeticFormService.findBySpeciesDbid(DBID)).thenReturn(SpeciesInputTestDto.COSMETIC_FORMS);
    }

    @Test
    public void test_updateAssociatedValues() {
        SpeciesInputTestDto input = new SpeciesInputTestDto();
        Species species = new Species();
        species.setDbid(DBID);
        setupUpdateAssociatedValues(species, input);
        speciesService.updateAssociatedValues(species, input);
        assertUpdateAssociatedValuesValid(species, input);
    }

    private void assertUpdateAssociatedValuesValid(Species species, SpeciesInputDto input) {
        assertEquals(SpeciesInputTestDto.ATTACKS, species.getAttacks());
        input.getAttacks().forEach(attack -> {
            verify(speciesAttackService, times(1)).update(species, attack);
        });

        assertEquals(SpeciesInputTestDto.ABILITIES, species.getAbilities());
        input.getAbilities().forEach(ability -> {
            verify(speciesAbilityService, times(1)).update(species, ability);
        });

        assertEquals(SpeciesInputTestDto.COSMETIC_FORMS, species.getCosmeticForms());
        input.getCosmeticForms().forEach(form -> {
            verify(cosmeticFormService, times(1)).update(species, form);
        });
    }
    
    private Species setup_deleteAssociatedValues() {
        Species species = new Species();
        
        SpeciesAttack speciesAttack = new SpeciesAttack();
        species.setAttacks(Collections.singletonList(speciesAttack));
        
        SpeciesAbility ability = new SpeciesAbility();
        species.setAbilities(Collections.singletonList(ability));
        
        CosmeticForm form = new CosmeticForm();
        species.setCosmeticForms(Collections.singleton(form));

        return species;
    }

    @Test
    public void test_deleteAssociatedValues() {
        Species species = setup_deleteAssociatedValues();
        speciesService.deleteAssociatedValues(species);
        assert_deleteAssociatedValues_Valid(species);
    }

    private void assert_deleteAssociatedValues_Valid(Species species) {
        species.getAttacks().forEach(attack -> {
            verify(speciesAttackService, times(1)).delete(attack);
        });
        species.getAbilities().forEach(ability -> {
            verify(speciesAbilityService, times(1)).delete(ability);
        });
        species.getCosmeticForms().forEach(form -> {
            verify(cosmeticFormService, times(1)).delete(form);
        });
    }
}