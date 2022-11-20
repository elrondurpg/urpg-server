package com.pokemonurpg.configuration.v1.gym.elitefour.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.elitefour.input.EliteFourInputTestDto;
import com.pokemonurpg.configuration.v1.gym.elitefour.model.EliteFour;
import com.pokemonurpg.configuration.v1.gym.elitefour.repository.EliteFourRepository;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.repository.EliteFourOwnershipTermRepository;
import com.pokemonurpg.stats.models.OwnedPokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class EliteFourServiceTest {
    private final static EliteFour MODEL = new EliteFour();

    @InjectMocks
    private EliteFourService service;

    @Mock
    private EliteFourRepository repository;

    @Mock
    private EliteFourOwnershipTermRepository eliteFourOwnershipTermRepository;
    
    @Mock
    private EliteFourPokemonService eliteFourPokemonService;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(EliteFour.class, service.getModelClass());
    }

    @Test
    public void test_findByCurrentOwnerRecord() {
        EliteFourOwnershipTerm record = new EliteFourOwnershipTerm();
        when(repository.findByCurrentOwnerRecord(record)).thenReturn(MODEL);
        assertEquals(MODEL, service.findByCurrentOwnerRecord(record));
    }

    @Test
    public void test_updateCurrentOwnerRecord() {
        EliteFourOwnershipTerm record = new EliteFourOwnershipTerm();
        service.updateCurrentOwnerRecord(MODEL, record);
        assertEquals(record, MODEL.getCurrentOwnerRecord());
        verify(repository, times(1)).save(MODEL);
    }

    private EliteFourInputTestDto setup_updateEmbeddedValues() {
        EliteFourInputTestDto input = new EliteFourInputTestDto();
        when(eliteFourOwnershipTermRepository.findByDbid(input.getCurrentOwnerRecordDbid())).thenReturn(EliteFourInputTestDto.OWNER_RECORD);
        return input;
    }

    @Test
    public void test_updateEmbeddedValues() {
        EliteFourInputTestDto input = setup_updateEmbeddedValues();
        service.updateEmbeddedValues(MODEL, input);
        assert_updateEmbeddedValues_Valid(input);
    }

    private void assert_updateEmbeddedValues_Valid(EliteFourInputTestDto input) {
        verify(eliteFourPokemonService, times(1)).updateAll(input, MODEL);
        assertEquals(EliteFourInputTestDto.OWNER_RECORD, MODEL.getCurrentOwnerRecord());
    }

    private EliteFourInputTestDto setup_updateEmbeddedValues_WithRemoveOwner() {
        EliteFourInputTestDto input = new EliteFourInputTestDto();
        input.setCurrentOwnerRecordDbid(null);
        input.setRemoveOwner(true);
        return input;
    }

    @Test
    public void test_updateEmbeddedValues_WithRemoveOwner() {
        EliteFourInputTestDto input = setup_updateEmbeddedValues_WithRemoveOwner();
        MODEL.setCurrentOwnerRecord(new EliteFourOwnershipTerm());
        Set<OwnedPokemon> pokemon = new HashSet<>();
        pokemon.add(new OwnedPokemon());
        MODEL.setPokemon(pokemon);
        service.updateEmbeddedValues(MODEL, input);
        assertNull(MODEL.getCurrentOwnerRecord());
        assertTrue(MODEL.getPokemon().isEmpty());
    }

}