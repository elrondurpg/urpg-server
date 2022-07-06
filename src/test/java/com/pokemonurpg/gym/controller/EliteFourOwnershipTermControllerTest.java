package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.input.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.gym.models.EliteFourOwnershipTerm;
import com.pokemonurpg.gym.service.EliteFourOwnershipTermService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EliteFourOwnershipTermControllerTest {
    private final static Date       DATE    = new Date();
    private final static Integer    DBID    = 432;
    private final static String     ELITE_FOUR     = "ELITE_FOUR";
    private final static String     OWNER   = "OWNER";

    @InjectMocks
    private EliteFourOwnershipTermController eliteFourOwnershipTermController;

    @Mock
    private EliteFourOwnershipTermService eliteFourOwnershipTermService;

    private EliteFourOwnershipTerm term = new EliteFourOwnershipTerm();
    private EliteFourOwnershipTermInputDto input = new EliteFourOwnershipTermInputDto();

    @Test
    public void findByDbid() {
        when(eliteFourOwnershipTermService.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, eliteFourOwnershipTermController.findByDbid(DBID));
    }

    // @Test
    // public void findByEliteFourAndOwnerAndOpenDate() {
    //     when(eliteFourOwnershipTermService.findBySlotAndOwnerAndOpenDate(ELITE_FOUR, OWNER, DATE)).thenReturn(term);
    //     assertEquals(term, eliteFourOwnershipTermController.findBySlotAndOwnerAndOpenDate(ELITE_FOUR, OWNER, DATE));
    // }

    @Test
    public void create() {
        when(eliteFourOwnershipTermService.create(input)).thenReturn(term);
        assertEquals(term, eliteFourOwnershipTermController.create(input));
    }

    @Test
    public void update() {
        when(eliteFourOwnershipTermService.update(input, DBID)).thenReturn(term);
        assertEquals(term, eliteFourOwnershipTermController.update(input, DBID));
    }

    @Test
    public void delete() {
        ResponseEntity response = eliteFourOwnershipTermController.delete(DBID);
        verify(eliteFourOwnershipTermService, times(1)).delete(DBID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(eliteFourOwnershipTermService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> eliteFourOwnershipTermController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}