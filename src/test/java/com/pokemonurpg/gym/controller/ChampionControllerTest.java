package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.input.ChampionInputDto;
import com.pokemonurpg.gym.models.Champion;
import com.pokemonurpg.gym.service.ChampionService;
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
public class ChampionControllerTest {
    private final static String         NAME        = "TEST";
    private final static Integer        DBID        = 2342;
    private final static List<String>   CHAMPIONS   = new ArrayList<>();

    @InjectMocks
    private ChampionController championController;

    @Mock
    private ChampionService championService;

    private Champion champion = new Champion();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNames() {
        when(championService.findAllNames()).thenReturn(CHAMPIONS);
        assertEquals(CHAMPIONS, championController.findAll());
    }

    @Test
    public void findByName() {
        when(championService.findByName(NAME)).thenReturn(champion);
        assertEquals(champion, championController.findByName(NAME));
    }

    @Test
    public void create() {
        ChampionInputDto input = new ChampionInputDto();
        input.setName(NAME);
        when(championService.create(input)).thenReturn(champion);
        assertEquals(champion, championController.create(input));
    }

    @Test
    public void update() {
        ChampionInputDto input = new ChampionInputDto();
        input.setName(NAME);
        when(championService.update(input, DBID)).thenReturn(champion);
        assertEquals(champion, championController.update(input, DBID));
    }

    @Test
    public void delete() {
        championController.delete(DBID);
        verify(championService, times(1)).delete(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(championService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> championController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}