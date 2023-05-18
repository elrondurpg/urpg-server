package com.pokemonurpg.attack.controller;

import com.pokemonurpg.configuration.v1.attacktargettypes.AttackTargetTypeController;
import com.pokemonurpg.entities.v1.AttackTargetType;
import com.pokemonurpg.configuration.v1.attacktargettypes.AttackTargetTypeInputDto;
import com.pokemonurpg.configuration.v1.attacktargettypes.AttackTargetTypeService;
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
public class AttackTargetTypeControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private AttackTargetTypeController attackTargetTypeController;

    @Mock
    private AttackTargetTypeService attackTargetTypeService;

    private AttackTargetType attackTargetType = new AttackTargetType();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(attackTargetTypeService.findAllNames()).thenReturn(names);
        assertEquals(names, attackTargetTypeController.findAllNames());
    }

    @Test
    public void findByName() {
        when(attackTargetTypeService.findByName(NAME)).thenReturn(attackTargetType);
        assertEquals(attackTargetType, attackTargetTypeController.findByName(NAME));
    }

    @Test
    public void create() {
        AttackTargetTypeInputDto input = new AttackTargetTypeInputDto();
        when(attackTargetTypeService.create(input)).thenReturn(attackTargetType);
        assertEquals(attackTargetType, attackTargetTypeController.create(input));
    }

    @Test
    public void update() {
        AttackTargetTypeInputDto input = new AttackTargetTypeInputDto();
        when(attackTargetTypeService.update(input, DBID)).thenReturn(attackTargetType);
        assertEquals(attackTargetType, attackTargetTypeController.update(input, DBID));
    }

}