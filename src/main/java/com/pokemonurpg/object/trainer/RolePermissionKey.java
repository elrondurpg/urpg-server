package com.pokemonurpg.object.trainer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RolePermissionKey implements Serializable {

    @Column(name = "role_dbid")
    private Integer roleDbid;

    @Column(name = "permission_dbid")
    private Integer permissionDbid;

    public RolePermissionKey() {
    }

    public RolePermissionKey(Integer roleDbid, Integer permissionDbid) {
        this.roleDbid = roleDbid;
        this.permissionDbid = permissionDbid;
    }

    public Integer getRoleDbid() {
        return roleDbid;
    }

    public void setRoleDbid(Integer roleDbid) {
        this.roleDbid = roleDbid;
    }

    public Integer getPermissionDbid() {
        return permissionDbid;
    }

    public void setPermissionDbid(Integer permissionDbid) {
        this.permissionDbid = permissionDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissionKey that = (RolePermissionKey) o;
        return Objects.equals(roleDbid, that.roleDbid) &&
                Objects.equals(permissionDbid, that.permissionDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleDbid, permissionDbid);
    }
}
