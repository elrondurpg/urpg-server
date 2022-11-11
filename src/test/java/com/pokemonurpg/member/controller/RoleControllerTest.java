package com.pokemonurpg.member.controller;

import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.input.RoleInputDto;
import com.pokemonurpg.member.service.RoleService;
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