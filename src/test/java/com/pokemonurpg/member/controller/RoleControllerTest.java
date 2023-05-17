package com.pokemonurpg.member.controller;

import com.pokemonurpg.configuration.v1.roles.RoleController;
import com.pokemonurpg.entities.Role;
import com.pokemonurpg.configuration.v1.roles.RoleInputDto;
import com.pokemonurpg.configuration.v1.roles.RoleService;
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
public class RoleControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private RoleController roleController;

    @Mock
    private RoleService roleService;

    private Role role = new Role();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(roleService.findAllNames()).thenReturn(names);
        assertEquals(names, roleController.findAllNames());
    }

    @Test
    public void findByName() {
        when(roleService.findByName(NAME)).thenReturn(role);
        assertEquals(role, roleController.findByName(NAME));
    }

    @Test
    public void create() {
        RoleInputDto input = new RoleInputDto();
        input.setName(NAME);
        when(roleService.create(input)).thenReturn(role);
        assertEquals(role, roleController.create(input));
    }

    @Test
    public void update() {
        RoleInputDto input = new RoleInputDto();
        input.setName(NAME);
        when(roleService.update(input, DBID)).thenReturn(role);
        assertEquals(role, roleController.update(input, DBID));
    }

}