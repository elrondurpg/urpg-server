package com.pokemonurpg.configuration.v1.roles;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoleRequestTest {

    @Test
    public void returnsEmptyListWhenRolesIsNull() {
        RoleRequest input = new RoleRequest();
        assertNotNull(input.getPermissions());
    }

}