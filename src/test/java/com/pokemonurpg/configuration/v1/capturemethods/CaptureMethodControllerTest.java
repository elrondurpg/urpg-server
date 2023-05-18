package com.pokemonurpg.configuration.v1.capturemethods;

import com.pokemonurpg.entities.v1.CaptureMethod;
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
public class CaptureMethodControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private CaptureMethodController obtainedController;

    @Mock
    private CaptureMethodService captureMethodService;

    private CaptureMethod captureMethod = new CaptureMethod();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(captureMethodService.findAllNames()).thenReturn(names);
        assertEquals(names, obtainedController.findAllNames());
    }

    @Test
    public void findByName() {
        when(captureMethodService.findByName(NAME)).thenReturn(captureMethod);
        assertEquals(captureMethod, obtainedController.findByName(NAME));
    }

    @Test
    public void create() {
        CaptureMethodRequest input = new CaptureMethodRequest();
        input.setName(NAME);
        when(captureMethodService.create(input)).thenReturn(captureMethod);
        assertEquals(captureMethod, obtainedController.create(input));
    }

    @Test
    public void update() {
        CaptureMethodRequest input = new CaptureMethodRequest();
        input.setName(NAME);
        when(captureMethodService.update(input, DBID)).thenReturn(captureMethod);
        assertEquals(captureMethod, obtainedController.update(input, DBID));
    }

}