package com.pokemonurpg.configuration.v1.roles;


import com.pokemonurpg.entities.v1.Role;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedRequest;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@UniqueName(type = Role.class)
public class RoleRequest implements UniquelyNamedRequest {
    @NotNull(groups = {ObjectCreation.class})
    @Size(min = 3, max = 21)
    private String name;

    private List<@Valid RolePermissionRequest> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RolePermissionRequest> getPermissions() {
        if (permissions != null) {
            return permissions;
        }
        else return Collections.emptyList();
    }

    public void setPermissions(List<RolePermissionRequest> permissions) {
        this.permissions = permissions;
    }
}
