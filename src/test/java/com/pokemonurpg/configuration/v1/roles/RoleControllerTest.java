package com.pokemonurpg.configuration.v1.roles;

import com.pokemonurpg.entities.v1.Role;
import com.pokemonurpg.login.v1.AuthorizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.converter.json.MappingJacksonValue;

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

    @Mock
    private AuthorizationService authorizationService;

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

        MappingJacksonValue value = roleController.findByName(NAME);

        assertEquals(role, value.getValue());
    }

    @Test
    public void create() {
        RoleRequest input = new RoleRequest();
        input.setName(NAME);
        when(roleService.create(input)).thenReturn(role);
        assertEquals(role, roleController.create(input));
    }

    @Test
    public void update() {
        RoleRequest input = new RoleRequest();
        input.setName(NAME);
        when(roleService.update(input, DBID)).thenReturn(role);
        assertEquals(role, roleController.update(input, DBID));
    }

}