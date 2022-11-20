package com.pokemonurpg.configuration.v1.gym.champion.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.champion.input.ChampionInputTestDto;
import com.pokemonurpg.configuration.v1.gym.champion.model.Champion;
import com.pokemonurpg.configuration.v1.gym.champion.repository.ChampionRepository;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.repository.ChampionOwnershipTermRepository;
import com.pokemonurpg.stats.models.OwnedPokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ChampionServiceTest {
    private final static Champion MODEL = new Champion();

    @InjectMocks
    private ChampionService service;

    @Mock
    private ChampionRepository repository;

    @Mock
    private ChampionOwnershipTermRepository championOwnershipTermRepository;
    
    @Mock
    private ChampionPokemonService championPokemonService;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(Champion.class, service.getModelClass());
    }

    @Test
    public void test_findByCurrentOwnerRecord() {
        ChampionOwnershipTerm record = new ChampionOwnershipTerm();
        when(repository.findByCurrentOwnerRecord(record)).thenReturn(MODEL);
        assertEquals(MODEL, service.findByCurrentOwnerRecord(record));
    }

    @Test
    public void test_updateCurrentOwnerRecord() {
        ChampionOwnershipTerm record = new ChampionOwnershipTerm();
        service.updateCurrentOwnerRecord(MODEL, record);
        assertEquals(record, MODEL.getCurrentOwnerRecord());
        verify(repository, times(1)).save(MODEL);
    }

    private ChampionInputTestDto setup_updateEmbeddedValues() {
        ChampionInputTestDto input = new ChampionInputTestDto();
        when(championOwnershipTermRepository.findByDbid(input.getCurrentOwnerRecordDbid())).thenReturn(ChampionInputTestDto.OWNER_RECORD);
        return input;
    }

    @Test
    public void test_updateEmbeddedValues() {
        ChampionInputTestDto input = setup_updateEmbeddedValues();
        service.updateEmbeddedValues(MODEL, input);
        assert_updateEmbeddedValues_Valid(input);
    }

    private void assert_updateEmbeddedValues_Valid(ChampionInputTestDto input) {
        verify(championPokemonService, times(1)).updateAll(input, MODEL);
        assertEquals(ChampionInputTestDto.OWNER_RECORD, MODEL.getCurrentOwnerRecord());
    }

    private ChampionInputTestDto setup_updateEmbeddedValues_WithRemoveOwner() {
        ChampionInputTestDto input = new ChampionInputTestDto();
        input.setCurrentOwnerRecordDbid(null);
        input.setRemoveOwner(true);
        return input;
    }

    @Test
    public void test_updateEmbeddedValues_WithRemoveOwner() {
        ChampionInputTestDto input = setup_updateEmbeddedValues_WithRemoveOwner();
        MODEL.setCurrentOwnerRecord(new ChampionOwnershipTerm());
        Set<OwnedPokemon> pokemon = new HashSet<>();
        pokemon.add(new OwnedPokemon());
        MODEL.setPokemon(pokemon);
        service.updateEmbeddedValues(MODEL, input);
        assertNull(MODEL.getCurrentOwnerRecord());
        assertTrue(MODEL.getPokemon().isEmpty());
    }

}