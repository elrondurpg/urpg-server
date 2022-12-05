package com.pokemonurpg.configuration.v1.member.role;


import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.member.models.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@UniqueName(type = Role.class)
public class RoleInputDto implements UniquelyNamedInputDto {
    @NotNull(groups = {ObjectCreation.class})
    @Size(min = 3, max = 21)
    private String name;

    private List<@Valid RolePermissionInputDto> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RolePermissionInputDto> getPermissions() {
        if (permissions != null) {
            return permissions;
        }
        else return Collections.emptyList();
    }

    public void setPermissions(List<RolePermissionInputDto> permissions) {
        this.permissions = permissions;
    }
}
