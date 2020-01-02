package com.pokemonurpg.repository;

import com.pokemonurpg.object.trainer.RolePermission;
import com.pokemonurpg.object.trainer.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionKey> {
    List<RolePermission> findByIdRoleDbid(int roleDbid);
    RolePermission findByIdRoleDbidAndIdPermissionDbid(int roleDbid, int permissionDbid);
}
