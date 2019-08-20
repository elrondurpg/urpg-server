package com.pokemonurpg.repository;

import com.pokemonurpg.object.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Permission findByDbid(int dbid);
}
