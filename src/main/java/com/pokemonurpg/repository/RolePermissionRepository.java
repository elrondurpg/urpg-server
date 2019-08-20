package com.pokemonurpg.repository;

import com.pokemonurpg.object.RolePermission;
import com.pokemonurpg.object.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionKey> {
    List<RolePermission> findByIdRoleDbid(int roleDbid);
}
