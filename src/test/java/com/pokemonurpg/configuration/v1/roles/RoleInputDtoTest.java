package com.pokemonurpg.configuration.v1.roles;

import com.pokemonurpg.configuration.v1.roles.RoleInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleInputDtoTest {

    @Test
    public void returnsEmptyListWhenRolesIsNull() {
        RoleInputDto input = new RoleInputDto();
        assertNotNull(input.getPermissions());
    }

}