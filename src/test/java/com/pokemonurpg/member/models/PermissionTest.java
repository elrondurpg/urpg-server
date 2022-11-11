package com.pokemonurpg.member.models;

import com.pokemonurpg.member.input.PermissionInputDto;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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