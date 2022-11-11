package com.pokemonurpg.member.controller;

import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.input.PermissionInputDto;
import com.pokemonurpg.member.service.PermissionService;
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
public class PermissionControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private PermissionController permissionController;

    @Mock
    private PermissionService permissionService;

    private Permission permission = new Permission();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(permissionService.findAllNames()).thenReturn(names);
        assertEquals(names, permissionController.findAllNames());
    }

    @Test
    public void findByName() {
        when(permissionService.findByName(NAME)).thenReturn(permission);
        assertEquals(permission, permissionController.findByName(NAME));
    }

    @Test
    public void create() {
        PermissionInputDto input = new PermissionInputDto();
        input.setName(NAME);
        when(permissionService.create(input)).thenReturn(permission);
        assertEquals(permission, permissionController.create(input));
    }

    @Test
    public void update() {
        PermissionInputDto input = new PermissionInputDto();
        input.setName(NAME);
        when(permissionService.update(input, DBID)).thenReturn(permission);
        assertEquals(permission, permissionController.update(input, DBID));
    }

}