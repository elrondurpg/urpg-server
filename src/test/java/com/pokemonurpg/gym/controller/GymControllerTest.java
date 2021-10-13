package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.gym.service.GymService;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GymControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;
    private final static List<String> GYMS = new ArrayList<>();

    @InjectMocks
    private GymController gymController;

    @Mock
    private GymService gymService;

    private Gym gym = new Gym();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNames() {
        when(gymService.findAllNames()).thenReturn(GYMS);
        assertEquals(GYMS, gymController.findAll());
    }

    @Test
    public void findByName() {
        when(gymService.findByName(NAME)).thenReturn(gym);
        assertEquals(gym, gymController.findByName(NAME));
    }

    @Test
    public void create() {
        GymInputDto input = new GymInputDto();
        input.setName(NAME);
        when(gymService.create(input)).thenReturn(gym);
        assertEquals(gym, gymController.create(input));
    }

    @Test
    public void update() {
        GymInputDto input = new GymInputDto();
        input.setName(NAME);
        when(gymService.update(input, DBID)).thenReturn(gym);
        assertEquals(gym, gymController.update(input, DBID));
    }

    @Test
    public void delete() {
        gymController.delete(DBID);
        verify(gymService, times(1)).delete(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(gymService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> gymController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}