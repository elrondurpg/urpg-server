package com.pokemonurpg.dto.security;

import com.pokemonurpg.object.trainer.Role;

import java.util.List;

public class RoleDto {
    private int dbid;
    private String name;
    private List<String> permissions;

    public RoleDto(Role role) {
        if (role != null) {
            setDbid(role.getDbid());
            setName(role.getName());
        }
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
