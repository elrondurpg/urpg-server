package com.pokemonurpg.configuration.v1.gym.gym.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.badge.service.BadgeService;
import com.pokemonurpg.configuration.v1.gym.gym.input.GymInputTestDto;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.gym.repository.GymRepository;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.repository.GymOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.stats.models.OwnedPokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class GymServiceTest {
    private final static Gym MODEL = new Gym();

    @InjectMocks
    private GymService service;

    @Mock
    private GymRepository repository;

    @Mock
    private BadgeService badgeService;

    @Mock
    private TypeService typeService;

    @Mock
    private GymOwnershipTermRepository gymOwnershipTermRepository;
    
    @Mock
    private GymPokemonService gymPokemonService;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(Gym.class, service.getModelClass());
    }

    @Test
    public void test_findByCurrentOwnerRecord() {
        GymOwnershipTerm record = new GymOwnershipTerm();
        when(repository.findByCurrentOwnerRecord(record)).thenReturn(MODEL);
        assertEquals(MODEL, service.findByCurrentOwnerRecord(record));
    }

    @Test
    public void test_updateCurrentOwnerRecord() {
        GymOwnershipTerm record = new GymOwnershipTerm();
        service.updateCurrentOwnerRecord(MODEL, record);
        assertEquals(record, MODEL.getCurrentOwnerRecord());
        verify(repository, times(1)).save(MODEL);
    }

    private GymInputTestDto setup_updateEmbeddedValues() {
        GymInputTestDto input = new GymInputTestDto();
        when(typeService.findByName(input.getType())).thenReturn(GymInputTestDto.TYPE);
        when(badgeService.findByName(input.getBadge())).thenReturn(GymInputTestDto.BADGE);
        when(gymOwnershipTermRepository.findByDbid(input.getCurrentOwnerRecordDbid())).thenReturn(GymInputTestDto.OWNER_RECORD);
        return input;
    }

    @Test
    public void test_updateEmbeddedValues() {
        GymInputTestDto input = setup_updateEmbeddedValues();
        service.updateEmbeddedValues(MODEL, input);
        assert_updateEmbeddedValues_Valid(input);
    }

    private void assert_updateEmbeddedValues_Valid(GymInputTestDto input) {
        assertEquals(GymInputTestDto.TYPE, MODEL.getType());
        assertEquals(GymInputTestDto.BADGE, MODEL.getBadge());
        verify(gymPokemonService, times(1)).updateAll(input, MODEL);
        assertEquals(GymInputTestDto.OWNER_RECORD, MODEL.getCurrentOwnerRecord());
    }

    private GymInputTestDto setup_updateEmbeddedValues_WithRemoveOwner() {
        GymInputTestDto input = new GymInputTestDto();
        input.setCurrentOwnerRecordDbid(null);
        input.setRemoveOwner(true);
        return input;
    }

    @Test
    public void test_updateEmbeddedValues_WithRemoveOwner() {
        GymInputTestDto input = setup_updateEmbeddedValues_WithRemoveOwner();
        MODEL.setCurrentOwnerRecord(new GymOwnershipTerm());
        Set<OwnedPokemon> pokemon = new HashSet<>();
        pokemon.add(new OwnedPokemon());
        MODEL.setPokemon(pokemon);
        service.updateEmbeddedValues(MODEL, input);
        assertNull(MODEL.getCurrentOwnerRecord());
        assertTrue(MODEL.getPokemon().isEmpty());
    }
}