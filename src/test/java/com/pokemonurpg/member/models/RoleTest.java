package com.pokemonurpg.member.models;

import com.pokemonurpg.configuration.v1.roles.RoleInputDto;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.Permission;
import com.pokemonurpg.entities.v1.Role;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RoleTest {
    private final static Integer DBID = 3432;
    private final static String NAME = "NAME";
    private final static Set<Permission> PERMISSIONS = new HashSet<>();
    private final static Set<Member> MEMBERS = new HashSet<>();

    @Test
    public void testPojo() {
        Role role = new Role();
        role.setDbid(DBID);
        role.setName(NAME);
        role.setPermissions(PERMISSIONS);
        role.setMembers(MEMBERS);

        assertEquals(DBID, role.getDbid());
        assertEquals(NAME, role.getName());
        assertEquals(PERMISSIONS, role.getPermissions());
        assertEquals(MEMBERS, role.getMembers());
    }

    @Test
    public void testConstructor() {
        RoleInputDto input = new RoleInputDto();
        input.setName(NAME);

        Role role = new Role(input);
        assertEquals(NAME, role.getName());
    }
}