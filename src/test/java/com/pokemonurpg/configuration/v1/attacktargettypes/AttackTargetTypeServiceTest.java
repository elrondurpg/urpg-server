package com.pokemonurpg.configuration.v1.attacktargettypes;

import com.pokemonurpg.entities.v1.AttackTargetType;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackTargetTypeRepository;
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
public class AttackTargetTypeServiceTest {
    private final static AttackTargetType ATTACK_TARGET_TYPE = mock(AttackTargetType.class);
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";
    private final static String DESCRIPTION = "TEST DESCRIPTION";

    @InjectMocks
    private AttackTargetTypeService attackTargetTypeService;

    @Mock
    private AttackTargetTypeRepository attackTargetTypeRepository;

    AttackTargetType attackTargetType = new AttackTargetType();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> categories = new ArrayList<>();
        when(attackTargetTypeRepository.findAllNames()).thenReturn(categories);

        assertEquals(categories, attackTargetTypeService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(attackTargetTypeRepository.findByDbid(DBID)).thenReturn(attackTargetType);
        assertEquals(attackTargetType, attackTargetTypeService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(attackTargetTypeRepository.findByName(NAME)).thenReturn(attackTargetType);
        assertEquals(attackTargetType, attackTargetTypeService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(attackTargetTypeRepository.findByName(NAME)).thenReturn(null);
        when(attackTargetTypeRepository.findFirstByNameStartingWith(NAME)).thenReturn(attackTargetType);
        assertEquals(attackTargetType, attackTargetTypeService.findByName(NAME));
    }

    @Test
    public void findByNameExact() {
        when(attackTargetTypeRepository.findByName(NAME)).thenReturn(ATTACK_TARGET_TYPE);
        assertEquals(ATTACK_TARGET_TYPE, attackTargetTypeService.findByNameExact(NAME));
    }

    @Test
    public void create() {
        AttackTargetTypeRequest input = new AttackTargetTypeRequest();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        AttackTargetType attackTargetType = attackTargetTypeService.create(input);
        assertEquals(NAME, attackTargetType.getName());
        assertEquals(DESCRIPTION, attackTargetType.getDescription());
        verify(attackTargetTypeRepository, times(1)).save(attackTargetType);
    }

    @Test
    public void updateExistingRecord() {
        AttackTargetTypeRequest input = new AttackTargetTypeRequest();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        when(attackTargetTypeRepository.findByDbid(DBID)).thenReturn(attackTargetType);

        AttackTargetType attackTargetType2 = attackTargetTypeService.update(input, DBID);
        assertEquals(attackTargetType, attackTargetType2);
        assertEquals(NAME, attackTargetType2.getName());
        assertEquals(DESCRIPTION, attackTargetType2.getDescription());
        verify(attackTargetTypeRepository, times(1)).save(attackTargetType2);
    }

    @Test
    public void updateNonExistingRecord() {
        AttackTargetTypeRequest input = new AttackTargetTypeRequest();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        when(attackTargetTypeRepository.findByDbid(DBID)).thenReturn(null);

        AttackTargetType attackTargetType2 = attackTargetTypeService.update(input, DBID);
        assertNull(attackTargetType2);
    }
}