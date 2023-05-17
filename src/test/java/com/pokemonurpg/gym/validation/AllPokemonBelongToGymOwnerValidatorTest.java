package com.pokemonurpg.gym.validation;

import com.pokemonurpg.lib.validators.AllPokemonBelongToGymOwnerValidator;
import com.pokemonurpg.lib.service.RequestPathVariableService;
import com.pokemonurpg.configuration.v1.gyms.GymPokemonInputDto;
import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AllPokemonBelongToGymOwnerValidatorTest {
    private final static Gym                GYM             = mock(Gym.class);
    private final static GymOwnershipTerm   TERM            = mock(GymOwnershipTerm.class);
    private final static Integer            GYM_DBID        = 1;
    private final static Integer            MEMBER_DBID     = 2;
    private final static Integer            O_MEMBER_DBID   = 3;
    private final static Integer            POKEMON_DBID    = 4;
    private final static Member             MEMBER          = mock(Member.class);
    private final static Member             OTHER_MEMBER    = mock(Member.class);
    private final static OwnedPokemon       POKEMON         = mock(OwnedPokemon.class);

    @InjectMocks
    private AllPokemonBelongToGymOwnerValidator validator;

    @Mock
    private GymService gymService;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    @Test
    public void isValid_ReturnsTrue_WhenInputIsNull() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenInputIsEmpty() {
        assertTrue(validator.isValid(new ArrayList<>(), null));
    }

    @Test
    public void initialize() {
        validator.initialize(null);
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestGymIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(GYM_DBID);
        when(gymService.findByDbid(GYM_DBID)).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenGymHasNoOwnerRecord() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(GYM_DBID);
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(GYM.getCurrentOwnerRecord()).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenOwnerRecordHasNoOwner() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(GYM_DBID);
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(GYM.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenAllPokemonAreOwnedByGymOwner() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(GYM_DBID);
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(GYM.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        when(ownedPokemonService.findByDbid(POKEMON_DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(MEMBER_DBID);
        assertTrue(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenAPokemonItemIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(GYM_DBID);
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(GYM.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        assertFalse(validator.isValid(createInputListWithNullInput(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenAPokemonDoesntExist() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(GYM_DBID);
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(GYM.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        when(ownedPokemonService.findByDbid(POKEMON_DBID)).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenAPokemonHasNoOwner() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(GYM_DBID);
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(GYM.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        when(ownedPokemonService.findByDbid(POKEMON_DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenAPokemonHasTheWrongOwner() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(GYM_DBID);
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(GYM.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        when(ownedPokemonService.findByDbid(POKEMON_DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(OTHER_MEMBER);
        when(MEMBER.getDbid()).thenReturn(MEMBER_DBID);
        when(OTHER_MEMBER.getDbid()).thenReturn(O_MEMBER_DBID);
        assertFalse(validator.isValid(createInputList(), null));
    }

    private List<GymPokemonInputDto> createInputList() {
        List<GymPokemonInputDto> list = new ArrayList<>();
        GymPokemonInputDto dto = new GymPokemonInputDto();
        dto.setDbid(POKEMON_DBID);
        list.add(dto);
        return list;
    }

    private List<GymPokemonInputDto> createInputListWithNullInput() {
        List<GymPokemonInputDto> list = new ArrayList<>();
        list.add(null);
        return list;
    }

}