package com.pokemonurpg.configuration.v1.capturemethods;

import com.pokemonurpg.entities.v1.CaptureMethod;
import com.pokemonurpg.infrastructure.v1.data.jpa.CaptureMethodRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CaptureMethodServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private CaptureMethodService captureMethodService;

    @Mock
    private CaptureMethodRepository captureMethodRepository;

    private CaptureMethod captureMethod = new CaptureMethod();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(captureMethodRepository.findAllNames()).thenReturn(types);

        assertEquals(types, captureMethodService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(captureMethodRepository.findByDbid(DBID)).thenReturn(captureMethod);
        assertEquals(captureMethod, captureMethodService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(captureMethodRepository.findByName(NAME)).thenReturn(captureMethod);
        assertEquals(captureMethod, captureMethodService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(captureMethodRepository.findByName(NAME)).thenReturn(null);
        when(captureMethodRepository.findFirstByNameStartingWith(NAME)).thenReturn(captureMethod);
        assertEquals(captureMethod, captureMethodService.findByName(NAME));
    }

    @Test
    public void create() {
        CaptureMethodRequest input = new CaptureMethodRequest();
        input.setName(NAME);

        CaptureMethod captureMethod = captureMethodService.create(input);
        assertEquals(NAME, captureMethod.getName());
        verify(captureMethodRepository, times(1)).save(captureMethod);
    }

    @Test
    public void updateExistingRecord() {
        CaptureMethodRequest input = new CaptureMethodRequest();
        input.setName(NAME);

        when(captureMethodRepository.findByDbid(DBID)).thenReturn(captureMethod);

        CaptureMethod captureMethod1 = captureMethodService.update(input, DBID);
        assertEquals(captureMethod, captureMethod1);
        assertEquals(NAME, captureMethod1.getName());
        verify(captureMethodRepository, times(1)).save(captureMethod1);
    }

    @Test
    public void updateNonExistingRecord() {
        CaptureMethodRequest input = new CaptureMethodRequest();
        input.setName(NAME);

        when(captureMethodRepository.findByDbid(DBID)).thenReturn(null);

        CaptureMethod captureMethod1 = captureMethodService.update(input, DBID);
        assertNull(captureMethod1);
    }

}