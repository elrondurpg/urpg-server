package com.pokemonurpg.member.input;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoleInputDtoTest {

    @Test
    public void returnsEmptyListWhenRolesIsNull() {
        RoleInputDto input = new RoleInputDto();
        assertNotNull(input.getPermissions());
    }

}