package com.pokemonurpg.configuration.v1.permissions;

import com.pokemonurpg.entities.v1.Permission;
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
        PermissionRequest input = new PermissionRequest();
        input.setName(NAME);
        when(permissionService.create(input)).thenReturn(permission);
        assertEquals(permission, permissionController.create(input));
    }

    @Test
    public void update() {
        PermissionRequest input = new PermissionRequest();
        input.setName(NAME);
        when(permissionService.update(input, DBID)).thenReturn(permission);
        assertEquals(permission, permissionController.update(input, DBID));
    }

}