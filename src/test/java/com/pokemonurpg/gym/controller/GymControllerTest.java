package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.gym.service.GymService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GymControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;
    private final static List<Gym> GYMS = new ArrayList<>();

    @InjectMocks
    private GymController gymController;

    @Mock
    private GymService gymService;

    private Gym gym = new Gym();

    @Test
    public void findAllNames() {
        when(gymService.findAll()).thenReturn(GYMS);
        assertEquals(GYMS, gymController.findAll());
    }

    @Test
    public void findByName() {
        when(gymService.findFirstActiveByName(NAME)).thenReturn(gym);
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

}