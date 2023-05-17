package com.pokemonurpg.object;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "role_permission")
@Entity
public class RolePermission {

    @EmbeddedId
    private RolePermissionKey id;

    public RolePermission() {
    }

    public RolePermission(RolePermissionKey id) {
        this.id = id;
    }

    public RolePermissionKey getId() {
        return id;
    }

    public void setId(RolePermissionKey id) {
        this.id = id;
    }
}