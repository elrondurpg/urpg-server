package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.permissions.PermissionInputDto;
import com.pokemonurpg.entities.v1.Permission;
import com.pokemonurpg.entities.v1.Role;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PermissionTest {

    private final static Integer DBID = 3232;
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Set<Role> ROLES = new HashSet<>();

    @Test
    public void testPojo() {
        Permission permission = new Permission();
        permission.setDescription(DESCRIPTION);
        permission.setDbid(DBID);
        permission.setName(NAME);
        permission.setRoles(ROLES);

        assertEquals(DESCRIPTION, permission.getDescription());
        assertEquals(DBID, permission.getDbid());
        assertEquals(NAME, permission.getName());
        assertEquals(ROLES, permission.getRoles());
    }

    @Test
    public void testConstructor() {
        PermissionInputDto input = new PermissionInputDto();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        Permission permission = new Permission(input);
        assertEquals(NAME, permission.getName());
        assertEquals(DESCRIPTION, permission.getDescription());
    }

}