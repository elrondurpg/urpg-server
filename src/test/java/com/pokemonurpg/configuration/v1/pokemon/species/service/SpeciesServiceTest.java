package com.pokemonurpg.configuration.v1.pokemon.species.service;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.configuration.v1.pokemon.species.input.CosmeticFormInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputTestDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.CosmeticForm;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAttack;
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
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpeciesServiceTest {

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
    public void createBase() {
        SpeciesInputTestDto input = new SpeciesInputTestDto();
        Species species = speciesService.createBase(input);
        assertNotNull(species);
    }

    @Test
    public void updateBase() {
        SpeciesInputTestDto input = new SpeciesInputTestDto();
        Species species = speciesService.createBase(input);
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
    public void updateEmbeddedValues() {
        SpeciesInputTestDto input = new SpeciesInputTestDto();
        setupUpdateEmbeddedValues(input);
        Species species = speciesService.createBase(input);
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

    /*private void setupUpdateAssociatedValues(Species species, SpeciesInputDto input) {
        List<SpeciesAttack> attacks = new ArrayList<>();
        input.getAttacks().forEach(attack -> {
            attacks.add(createSpeciesAttackForName(attack.getName()));
        });
        return attacks;
    }

    @Test
    public void updateAssociatedValues() {
        SpeciesInputTestDto input = new SpeciesInputTestDto();
        Species species = speciesService.createBase(input);
        setupUpdateAssociatedValues(species, input);
        speciesService.updateEmbeddedValues(species, input);
        assertUpdateEmbeddedValuesValid(species, input);
    }

    private void assertUpdateAssociatedValuesValid(Species species, SpeciesInputDto input) {
        List<SpeciesAbility> abilities = species.getAbilities();
        assertNotNull(abilities);
        input.getAbilities().forEach(ability -> {
            assertTrue(abilities.stream().anyMatch(foundAbility -> ability.getName().equals(foundAbility.getAbility().getName())));
        });

        List<SpeciesAttack> attacks = species.getAttacks();
        assertNotNull(attacks);
        input.getAbilities().forEach(attack -> {
            assertTrue(attacks.stream().anyMatch(foundAttack -> attack.getName().equals(foundAttack.getAttack().getName())));
        });

        Set<CosmeticForm> forms = species.getCosmeticForms();
        assertNotNull(forms);
        input.getAbilities().forEach(form -> {
            assertTrue(forms.stream().anyMatch(foundForm -> form.getName().equals(foundForm.getName())));
        });
    }*/
    
}