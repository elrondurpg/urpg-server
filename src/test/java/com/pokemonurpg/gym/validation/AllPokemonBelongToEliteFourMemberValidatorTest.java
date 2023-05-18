package com.pokemonurpg.gym.validation;

import com.pokemonurpg.lib.v1.validators.AllPokemonBelongToEliteFourMemberValidator;
import com.pokemonurpg.lib.v1.service.RequestPathVariableService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourPokemonInputDto;
import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.entities.v1.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.stats.v1.OwnedPokemonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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