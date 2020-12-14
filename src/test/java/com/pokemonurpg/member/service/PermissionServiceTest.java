package com.pokemonurpg.member.service;

import com.pokemonurpg.member.input.PermissionInputDto;
import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.repository.PermissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PermissionServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private PermissionService permissionService;

    @Mock
    private PermissionRepository permissionRepository;

    private Permission permission = new Permission();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(permissionRepository.findAllNames()).thenReturn(types);

        assertEquals(types, permissionService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(permissionRepository.findByDbid(DBID)).thenReturn(permission);
        assertEquals(permission, permissionService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(permissionRepository.findByName(NAME)).thenReturn(permission);
        assertEquals(permission, permissionService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(permissionRepository.findByName(NAME)).thenReturn(null);
        when(permissionRepository.findFirstByNameStartingWith(NAME)).thenReturn(permission);
        assertEquals(permission, permissionService.findByName(NAME));
    }

    @Test
    public void create() {
        PermissionInputDto input = new PermissionInputDto();
        input.setName(NAME);

        Permission permission = permissionService.create(input);
        assertEquals(NAME, permission.getName());
        verify(permissionRepository, times(1)).save(permission);
    }

    @Test
    public void updateExistingRecord() {
        PermissionInputDto input = new PermissionInputDto();
        input.setName(NAME);

        when(permissionRepository.findByDbid(DBID)).thenReturn(permission);

        Permission permission1 = permissionService.update(input, DBID);
        assertEquals(permission, permission1);
        assertEquals(NAME, permission1.getName());
        verify(permissionRepository, times(1)).save(permission1);
    }

    @Test
    public void updateNonExistingRecord() {
        PermissionInputDto input = new PermissionInputDto();
        input.setName(NAME);

        when(permissionRepository.findByDbid(DBID)).thenReturn(null);

        Permission permission1 = permissionService.update(input, DBID);
        assertNull(permission1);
        verify(permissionRepository, times(0)).save(Matchers.any());
    }

}