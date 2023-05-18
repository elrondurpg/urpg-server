package com.pokemonurpg.configuration.v1.contestattributes;

import com.pokemonurpg.entities.v1.ContestAttribute;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestAttributeRepository;
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
public class ContestAttributeServiceTest {
    private final static ContestAttribute    CONTEST_ATTRIBUTE = mock(ContestAttribute.class);
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private ContestAttributeService contestAttributeService;

    @Mock
    private ContestAttributeRepository contestAttributeRepository;

    private ContestAttribute contestAttribute = new ContestAttribute();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(contestAttributeRepository.findAllNames()).thenReturn(types);

        assertEquals(types, contestAttributeService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(contestAttributeRepository.findByDbid(DBID)).thenReturn(contestAttribute);
        assertEquals(contestAttribute, contestAttributeService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(contestAttributeRepository.findByName(NAME)).thenReturn(contestAttribute);
        assertEquals(contestAttribute, contestAttributeService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(contestAttributeRepository.findByName(NAME)).thenReturn(null);
        when(contestAttributeRepository.findFirstByNameStartingWith(NAME)).thenReturn(contestAttribute);
        assertEquals(contestAttribute, contestAttributeService.findByName(NAME));
    }

    @Test
    public void findByNameExact() {
        when(contestAttributeRepository.findByName(NAME)).thenReturn(CONTEST_ATTRIBUTE);
        assertEquals(CONTEST_ATTRIBUTE, contestAttributeService.findByNameExact(NAME));
    }

    @Test
    public void create() {
        ContestAttributeRequest input = new ContestAttributeRequest();
        input.setName(NAME);

        ContestAttribute contestAttribute = contestAttributeService.create(input);
        assertEquals(NAME, contestAttribute.getName());
        verify(contestAttributeRepository, times(1)).save(contestAttribute);
    }

    @Test
    public void updateExistingRecord() {
        ContestAttributeRequest input = new ContestAttributeRequest();
        input.setName(NAME);

        when(contestAttributeRepository.findByDbid(DBID)).thenReturn(contestAttribute);

        ContestAttribute contestAttribute1 = contestAttributeService.update(input, DBID);
        assertEquals(contestAttribute, contestAttribute1);
        assertEquals(NAME, contestAttribute1.getName());
        verify(contestAttributeRepository, times(1)).save(contestAttribute1);
    }

    @Test
    public void updateNonExistingRecord() {
        ContestAttributeRequest input = new ContestAttributeRequest();
        input.setName(NAME);

        when(contestAttributeRepository.findByDbid(DBID)).thenReturn(null);

        ContestAttribute contestAttribute1 = contestAttributeService.update(input, DBID);
        assertNull(contestAttribute1);
    }

}