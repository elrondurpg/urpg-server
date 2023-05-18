package com.pokemonurpg.configuration.v1.elitefourmemberslots;

import com.pokemonurpg.entities.v1.EliteFourMemberSlot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EliteFourMemberSlotMemberSlotControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;
    private final static List<String> ELITE_FOURS = new ArrayList<>();

    @InjectMocks
    private EliteFourMemberSlotController eliteFourMemberSlotController;

    @Mock
    private EliteFourMemberSlotService eliteFourMemberSlotService;

    private EliteFourMemberSlot eliteFourMemberSlot = new EliteFourMemberSlot();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNames() {
        when(eliteFourMemberSlotService.findAllNames()).thenReturn(ELITE_FOURS);
        assertEquals(ELITE_FOURS, eliteFourMemberSlotController.findAll());
    }

    @Test
    public void findByName() {
        when(eliteFourMemberSlotService.findByName(NAME)).thenReturn(eliteFourMemberSlot);
        assertEquals(eliteFourMemberSlot, eliteFourMemberSlotController.findByName(NAME));
    }

    @Test
    public void create() {
        EliteFourMemberSlotRequest input = new EliteFourMemberSlotRequest();
        input.setName(NAME);
        when(eliteFourMemberSlotService.create(input)).thenReturn(eliteFourMemberSlot);
        assertEquals(eliteFourMemberSlot, eliteFourMemberSlotController.create(input));
    }

    @Test
    public void update() {
        EliteFourMemberSlotRequest input = new EliteFourMemberSlotRequest();
        input.setName(NAME);
        when(eliteFourMemberSlotService.update(input, DBID)).thenReturn(eliteFourMemberSlot);
        assertEquals(eliteFourMemberSlot, eliteFourMemberSlotController.update(input, DBID));
    }

    @Test
    public void delete() {
        eliteFourMemberSlotController.delete(DBID);
        verify(eliteFourMemberSlotService, times(1)).delete(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(eliteFourMemberSlotService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> eliteFourMemberSlotController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}