package com.pokemonurpg.member.service;

import com.pokemonurpg.configuration.v1.roles.RoleInputDto;
import com.pokemonurpg.configuration.v1.roles.RolePermissionInputDto;
import com.pokemonurpg.entities.Permission;
import com.pokemonurpg.entities.Role;
import com.pokemonurpg.infrastructure.data.RoleRepository;
import com.pokemonurpg.configuration.v1.permissions.PermissionService;
import com.pokemonurpg.configuration.v1.roles.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";
    private final static Permission CURRENT_PERMISSION = mock(Permission.class);
    private final static String CURRENT_PERMISSION_NAME = "CURRENT_PERMISSION_NAME";
    private final static Permission NEW_PERMISSION = mock(Permission.class);
    private final static String NEW_PERMISSION_NAME = "NEW_PERMISSION_NAME";
    private final static Role CURRENT_ROLE = new Role();

    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    private Role role = new Role();

    @Mock
    private PermissionService permissionService;

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(roleRepository.findAllNames()).thenReturn(types);

        assertEquals(types, roleService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(roleRepository.findByDbid(DBID)).thenReturn(role);
        assertEquals(role, roleService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(roleRepository.findByName(NAME)).thenReturn(role);
        assertEquals(role, roleService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(roleRepository.findByName(NAME)).thenReturn(null);
        when(roleRepository.findFirstByNameStartingWith(NAME)).thenReturn(role);
        assertEquals(role, roleService.findByName(NAME));
    }

    @Test
    public void create() {
        // Given a Permission "newPermission" known by name "NEW_PERMISSION_NAME"
        when(permissionService.findByName(NEW_PERMISSION_NAME)).thenReturn(NEW_PERMISSION);

        // Given a RolePermissionInputDto with name = "NEW_PERMISSION_NAME"
        RolePermissionInputDto permInput1 = new RolePermissionInputDto();
        permInput1.setName(NEW_PERMISSION_NAME);

        // Given a RoleInputDto whose "permissions" list includes permInput1
        RoleInputDto input = new RoleInputDto();
        input.setName(NAME);
        input.setPermissions(Collections.singletonList(permInput1));

        // When I call roleService.create(input)
        Role role = roleService.create(input);
        assertEquals(NAME, role.getName());
        verify(roleRepository, times(1)).save(role);

        // Then that role's permissions will contain NEW_PERMISSION
        assertTrue(role.getPermissions().contains(NEW_PERMISSION));
    }

    @Test
    public void updateExistingRecord() {
        // Given a Permission "currentPermission" known by name "CURRENT_PERMISSION_NAME"
        when(permissionService.findByName(CURRENT_PERMISSION_NAME)).thenReturn(CURRENT_PERMISSION);

        // Given a Permission "newPermission" known by name "NEW_PERMISSION_NAME"
        when(permissionService.findByName(NEW_PERMISSION_NAME)).thenReturn(NEW_PERMISSION);

        // Given a role that has permission CURRENT_PERMISSION
        Set<Permission> currentPermissions = new HashSet<>();
        currentPermissions.add(CURRENT_PERMISSION);
        CURRENT_ROLE.setPermissions(currentPermissions);

        // Given a RolePermissionInputDto with name = "NEW_PERMISSION_NAME"
        RolePermissionInputDto permInput1 = new RolePermissionInputDto();
        permInput1.setName(NEW_PERMISSION_NAME);

        // Given a RolePermissionInputDto with name = "CURRENT_PERMISSION_NAME" and delete = true
        RolePermissionInputDto permInput2 = new RolePermissionInputDto();
        permInput2.setName(CURRENT_PERMISSION_NAME);
        permInput2.setDelete(true);

        // Given a RoleInputDto whose "permissions" list includes permInput1 and permInput2
        RoleInputDto input = new RoleInputDto();
        input.setName(NAME);
        input.setPermissions(Arrays.asList(permInput1, permInput2));

        when(roleRepository.findByDbid(DBID)).thenReturn(CURRENT_ROLE);

        // When I call roleService.updateAll(input, DBID)
        Role newRole = roleService.update(input, DBID);
        assertEquals(NAME, newRole.getName());
        verify(roleRepository, times(1)).save(newRole);

        // Then currentPermissions will contain NEW_PERMISSION and not CURRENT_PERMISSION
        assertTrue(currentPermissions.contains(NEW_PERMISSION));
        assertFalse(currentPermissions.contains(CURRENT_PERMISSION));
    }

    @Test
    public void updateNonExistingRecord() {
        RoleInputDto input = new RoleInputDto();
        input.setName(NAME);

        when(roleRepository.findByDbid(DBID)).thenReturn(null);

        Role role1 = roleService.update(input, DBID);
        assertNull(role1);
        verify(roleRepository, times(0)).save(Matchers.any());
    }

}