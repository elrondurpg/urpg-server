package com.pokemonurpg.gym.controller;

import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermController;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermInputDto;
import com.pokemonurpg.entities.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class GymOwnershipTermControllerTest {
    private final static Date       DATE    = new Date();
    private final static Integer    DBID    = 432;
    private final static String     GYM     = "GYM";
    private final static String     OWNER   = "OWNER";

    @InjectMocks
    private GymOwnershipTermController gymOwnershipTermController;

    @Mock
    private GymOwnershipTermService gymOwnershipTermService;

    private GymOwnershipTerm term = new GymOwnershipTerm();
    private GymOwnershipTermInputDto input = new GymOwnershipTermInputDto();

    @Test
    public void findByDbid() {
        when(gymOwnershipTermService.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, gymOwnershipTermController.findByDbid(DBID));
    }

    // @Test
    // public void findByGymAndOwnerAndOpenDate() {
    //     when(gymOwnershipTermService.findByGymAndOwnerAndOpenDate(GYM, OWNER, DATE)).thenReturn(term);
    //     assertEquals(term, gymOwnershipTermController.findByGymAndOwnerAndOpenDate(GYM, OWNER, DATE));
    // }

    @Test
    public void create() {
        when(gymOwnershipTermService.create(input)).thenReturn(term);
        assertEquals(term, gymOwnershipTermController.create(input));
    }

    @Test
    public void update() {
        when(gymOwnershipTermService.update(input, DBID)).thenReturn(term);
        assertEquals(term, gymOwnershipTermController.update(input, DBID));
    }

    @Test
    public void delete() {
        ResponseEntity response = gymOwnershipTermController.delete(DBID);
        verify(gymOwnershipTermService, times(1)).delete(DBID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(gymOwnershipTermService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> gymOwnershipTermController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}