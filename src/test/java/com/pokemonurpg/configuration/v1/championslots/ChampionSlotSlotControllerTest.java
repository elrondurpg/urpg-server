package com.pokemonurpg.configuration.v1.championslots;

import com.pokemonurpg.entities.v1.ChampionSlot;
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
public class ChampionSlotSlotControllerTest {
    private final static String         NAME        = "TEST";
    private final static Integer        DBID        = 2342;
    private final static List<String>   CHAMPIONS   = new ArrayList<>();

    @InjectMocks
    private ChampionSlotController championSlotController;

    @Mock
    private ChampionSlotService championSlotService;

    private ChampionSlot championSlot = new ChampionSlot();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNames() {
        when(championSlotService.findAllNames()).thenReturn(CHAMPIONS);
        assertEquals(CHAMPIONS, championSlotController.findAll());
    }

    @Test
    public void findByName() {
        when(championSlotService.findByName(NAME)).thenReturn(championSlot);
        assertEquals(championSlot, championSlotController.findByName(NAME));
    }

    @Test
    public void create() {
        ChampionSlotRequest input = new ChampionSlotRequest();
        input.setName(NAME);
        when(championSlotService.create(input)).thenReturn(championSlot);
        assertEquals(championSlot, championSlotController.create(input));
    }

    @Test
    public void update() {
        ChampionSlotRequest input = new ChampionSlotRequest();
        input.setName(NAME);
        when(championSlotService.update(input, DBID)).thenReturn(championSlot);
        assertEquals(championSlot, championSlotController.update(input, DBID));
    }

    @Test
    public void delete() {
        championSlotController.delete(DBID);
        verify(championSlotService, times(1)).delete(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(championSlotService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> championSlotController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}