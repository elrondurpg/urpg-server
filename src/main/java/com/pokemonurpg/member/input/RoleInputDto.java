package com.pokemonurpg.member.input;


import com.pokemonurpg.core.validation.ObjectCreation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

public class RoleInputDto  {
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
