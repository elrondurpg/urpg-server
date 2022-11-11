package com.pokemonurpg.gym.validation;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.gym.input.EliteFourPokemonInputDto;
import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.gym.models.EliteFourOwnershipTerm;
import com.pokemonurpg.gym.service.EliteFourService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AllPokemonBelongToEliteFourMemberValidatorTest {
    private final static EliteFour                  ELITE_FOUR      = mock(EliteFour.class);
    private final static EliteFourOwnershipTerm     TERM            = mock(EliteFourOwnershipTerm.class);
    private final static Integer                    ELITE_FOUR_DBID = 1;
    private final static Integer                    MEMBER_DBID     = 2;
    private final static Integer                    O_MEMBER_DBID   = 3;
    private final static Integer                    POKEMON_DBID    = 4;
    private final static Member                     MEMBER          = mock(Member.class);
    private final static Member                     OTHER_MEMBER    = mock(Member.class);
    private final static OwnedPokemon               POKEMON         = mock(OwnedPokemon.class);

    @InjectMocks
    private AllPokemonBelongToEliteFourMemberValidator validator;

    @Mock
    private EliteFourService eliteFourService;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    @Test
    public void initialize() {
        validator.initialize(null);
    }

    @Test
    public void isValid_ReturnsTrue_WhenInputIsNull() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenInputIsEmpty() {
        assertTrue(validator.isValid(new ArrayList<>(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestEliteFourIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(ELITE_FOUR_DBID);
        when(eliteFourService.findByDbid(ELITE_FOUR_DBID)).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenEliteFourHasNoOwnerRecord() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(ELITE_FOUR_DBID);
        when(eliteFourService.findByDbid(ELITE_FOUR_DBID)).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getCurrentOwnerRecord()).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenOwnerRecordHasNoOwner() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(ELITE_FOUR_DBID);
        when(eliteFourService.findByDbid(ELITE_FOUR_DBID)).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenAllPokemonAreOwnedByEliteFourOwner() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(ELITE_FOUR_DBID);
        when(eliteFourService.findByDbid(ELITE_FOUR_DBID)).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        when(ownedPokemonService.findByDbid(POKEMON_DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(MEMBER_DBID);
        assertTrue(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenAPokemonItemIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(ELITE_FOUR_DBID);
        when(eliteFourService.findByDbid(ELITE_FOUR_DBID)).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        assertFalse(validator.isValid(createInputListWithNullInput(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenAPokemonDoesntExist() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(ELITE_FOUR_DBID);
        when(eliteFourService.findByDbid(ELITE_FOUR_DBID)).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        when(ownedPokemonService.findByDbid(POKEMON_DBID)).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenAPokemonHasNoOwner() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(ELITE_FOUR_DBID);
        when(eliteFourService.findByDbid(ELITE_FOUR_DBID)).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        when(ownedPokemonService.findByDbid(POKEMON_DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(null);
        assertFalse(validator.isValid(createInputList(), null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenAPokemonHasTheWrongOwner() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(ELITE_FOUR_DBID);
        when(eliteFourService.findByDbid(ELITE_FOUR_DBID)).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getCurrentOwnerRecord()).thenReturn(TERM);
        when(TERM.getOwner()).thenReturn(MEMBER);
        when(ownedPokemonService.findByDbid(POKEMON_DBID)).thenReturn(POKEMON);
        when(POKEMON.getTrainer()).thenReturn(OTHER_MEMBER);
        when(MEMBER.getDbid()).thenReturn(MEMBER_DBID);
        when(OTHER_MEMBER.getDbid()).thenReturn(O_MEMBER_DBID);
        assertFalse(validator.isValid(createInputList(), null));
    }

    private List<EliteFourPokemonInputDto> createInputList() {
        List<EliteFourPokemonInputDto> list = new ArrayList<>();
        EliteFourPokemonInputDto dto = new EliteFourPokemonInputDto();
        dto.setDbid(POKEMON_DBID);
        list.add(dto);
        return list;
    }

    private List<EliteFourPokemonInputDto> createInputListWithNullInput() {
        List<EliteFourPokemonInputDto> list = new ArrayList<>();
        list.add(null);
        return list;
    }

}