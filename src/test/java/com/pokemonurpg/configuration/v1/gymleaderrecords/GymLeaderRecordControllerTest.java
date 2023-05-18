package com.pokemonurpg.configuration.v1.gymleaderrecords;

import com.pokemonurpg.entities.v1.GymLeaderRecord;
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
public class GymLeaderRecordControllerTest {
    private final static Date       DATE    = new Date();
    private final static Integer    DBID    = 432;
    private final static String     GYM     = "GYM";
    private final static String     OWNER   = "OWNER";

    @InjectMocks
    private GymLeaderRecordController gymLeaderRecordController;

    @Mock
    private GymLeaderRecordService gymLeaderRecordService;

    private GymLeaderRecord term = new GymLeaderRecord();
    private GymLeaderRecordRequest input = new GymLeaderRecordRequest();

    @Test
    public void findByDbid() {
        when(gymLeaderRecordService.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, gymLeaderRecordController.findByDbid(DBID));
    }

    // @Test
    // public void findByGymAndOwnerAndOpenDate() {
    //     when(gymOwnershipTermService.findByGymAndOwnerAndOpenDate(GYM, OWNER, DATE)).thenReturn(term);
    //     assertEquals(term, gymOwnershipTermController.findByGymAndOwnerAndOpenDate(GYM, OWNER, DATE));
    // }

    @Test
    public void create() {
        when(gymLeaderRecordService.create(input)).thenReturn(term);
        assertEquals(term, gymLeaderRecordController.create(input));
    }

    @Test
    public void update() {
        when(gymLeaderRecordService.update(input, DBID)).thenReturn(term);
        assertEquals(term, gymLeaderRecordController.update(input, DBID));
    }

    @Test
    public void delete() {
        ResponseEntity response = gymLeaderRecordController.delete(DBID);
        verify(gymLeaderRecordService, times(1)).delete(DBID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(gymLeaderRecordService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> gymLeaderRecordController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}