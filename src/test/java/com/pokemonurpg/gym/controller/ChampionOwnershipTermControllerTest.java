package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.input.ChampionOwnershipTermInputDto;
import com.pokemonurpg.gym.models.ChampionOwnershipTerm;
import com.pokemonurpg.gym.service.ChampionOwnershipTermService;
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
public class ChampionOwnershipTermControllerTest {
    private final static Date       DATE        = new Date();
    private final static Integer    DBID        = 432;
    private final static String     CHAMPION    = "CHAMPION";
    private final static String     OWNER       = "OWNER";

    @InjectMocks
    private ChampionOwnershipTermController championOwnershipTermController;

    @Mock
    private ChampionOwnershipTermService championOwnershipTermService;

    private ChampionOwnershipTerm term = new ChampionOwnershipTerm();
    private ChampionOwnershipTermInputDto input = new ChampionOwnershipTermInputDto();

    @Test
    public void findByDbid() {
        when(championOwnershipTermService.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, championOwnershipTermController.findByDbid(DBID));
    }

    // @Test
    // public void findByChampionAndOwnerAndOpenDate() {
    //     when(championOwnershipTermService.findBySlotAndOwnerAndOpenDate(CHAMPION, OWNER, DATE)).thenReturn(term);
    //     assertEquals(term, championOwnershipTermController.findBySlotAndOwnerAndOpenDate(CHAMPION, OWNER, DATE));
    // }

    @Test
    public void create() {
        when(championOwnershipTermService.create(input)).thenReturn(term);
        assertEquals(term, championOwnershipTermController.create(input));
    }

    @Test
    public void update() {
        when(championOwnershipTermService.update(input, DBID)).thenReturn(term);
        assertEquals(term, championOwnershipTermController.update(input, DBID));
    }

    @Test
    public void delete() {
        ResponseEntity response = championOwnershipTermController.delete(DBID);
        verify(championOwnershipTermService, times(1)).delete(DBID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(championOwnershipTermService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> championOwnershipTermController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}