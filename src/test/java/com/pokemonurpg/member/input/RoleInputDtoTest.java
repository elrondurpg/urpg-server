package com.pokemonurpg.member.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleInputDtoTest {

    @Test
    public void returnsEmptyListWhenRolesIsNull() {
        RoleInputDto input = new RoleInputDto();
        assertNotNull(input.getPermissions());
    }

}