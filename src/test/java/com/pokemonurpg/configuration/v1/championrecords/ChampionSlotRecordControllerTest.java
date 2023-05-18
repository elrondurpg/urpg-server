package com.pokemonurpg.configuration.v1.championrecords;

import com.pokemonurpg.entities.v1.ChampionRecord;
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
public class ChampionSlotRecordControllerTest {
    private final static Date       DATE        = new Date();
    private final static Integer    DBID        = 432;
    private final static String     CHAMPION    = "CHAMPION";
    private final static String     OWNER       = "OWNER";

    @InjectMocks
    private ChampionRecordController championRecordController;

    @Mock
    private ChampionRecordService championRecordService;

    private ChampionRecord term = new ChampionRecord();
    private ChampionRecordRequest input = new ChampionRecordRequest();

    @Test
    public void findByDbid() {
        when(championRecordService.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, championRecordController.findByDbid(DBID));
    }

    // @Test
    // public void findByChampionAndOwnerAndOpenDate() {
    //     when(championOwnershipTermService.findBySlotAndOwnerAndOpenDate(CHAMPION, OWNER, DATE)).thenReturn(term);
    //     assertEquals(term, championOwnershipTermController.findBySlotAndOwnerAndOpenDate(CHAMPION, OWNER, DATE));
    // }

    @Test
    public void create() {
        when(championRecordService.create(input)).thenReturn(term);
        assertEquals(term, championRecordController.create(input));
    }

    @Test
    public void update() {
        when(championRecordService.update(input, DBID)).thenReturn(term);
        assertEquals(term, championRecordController.update(input, DBID));
    }

    @Test
    public void delete() {
        ResponseEntity response = championRecordController.delete(DBID);
        verify(championRecordService, times(1)).delete(DBID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(championRecordService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> championRecordController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}