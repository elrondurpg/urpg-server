package com.pokemonurpg.general.controller;

import com.pokemonurpg.general.models.Obtained;
import com.pokemonurpg.general.input.ObtainedInputDto;
import com.pokemonurpg.general.service.ObtainedService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ObtainedControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private ObtainedController obtainedController;

    @Mock
    private ObtainedService obtainedService;

    private Obtained obtained = new Obtained();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(obtainedService.findAllNames()).thenReturn(names);
        assertEquals(names, obtainedController.findAllNames());
    }

    @Test
    public void findByName() {
        when(obtainedService.findByName(NAME)).thenReturn(obtained);
        assertEquals(obtained, obtainedController.findByName(NAME));
    }

    @Test
    public void create() {
        ObtainedInputDto input = new ObtainedInputDto();
        input.setName(NAME);
        when(obtainedService.create(input)).thenReturn(obtained);
        assertEquals(obtained, obtainedController.create(input));
    }

    @Test
    public void update() {
        ObtainedInputDto input = new ObtainedInputDto();
        input.setName(NAME);
        when(obtainedService.update(input, DBID)).thenReturn(obtained);
        assertEquals(obtained, obtainedController.update(input, DBID));
    }

}