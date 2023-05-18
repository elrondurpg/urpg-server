package com.pokemonurpg.configuration.v1.elitefourmemberrecords;

import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordController;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordRequest;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordService;
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
public class EliteFourMemberSlotOwnershipTermControllerTest {
    private final static Date       DATE    = new Date();
    private final static Integer    DBID    = 432;
    private final static String     ELITE_FOUR     = "ELITE_FOUR";
    private final static String     OWNER   = "OWNER";

    @InjectMocks
    private EliteFourRecordController eliteFourRecordController;

    @Mock
    private EliteFourRecordService eliteFourRecordService;

    private EliteFourMemberRecord term = new EliteFourMemberRecord();
    private EliteFourRecordRequest input = new EliteFourRecordRequest();

    @Test
    public void findByDbid() {
        when(eliteFourRecordService.findByDbid(DBID)).thenReturn(term);
        assertEquals(term, eliteFourRecordController.findByDbid(DBID));
    }

    // @Test
    // public void findByEliteFourAndOwnerAndOpenDate() {
    //     when(eliteFourOwnershipTermService.findBySlotAndOwnerAndOpenDate(ELITE_FOUR, OWNER, DATE)).thenReturn(term);
    //     assertEquals(term, eliteFourOwnershipTermController.findBySlotAndOwnerAndOpenDate(ELITE_FOUR, OWNER, DATE));
    // }

    @Test
    public void create() {
        when(eliteFourRecordService.create(input)).thenReturn(term);
        assertEquals(term, eliteFourRecordController.create(input));
    }

    @Test
    public void update() {
        when(eliteFourRecordService.update(input, DBID)).thenReturn(term);
        assertEquals(term, eliteFourRecordController.update(input, DBID));
    }

    @Test
    public void delete() {
        ResponseEntity response = eliteFourRecordController.delete(DBID);
        verify(eliteFourRecordService, times(1)).delete(DBID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(eliteFourRecordService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> eliteFourRecordController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}