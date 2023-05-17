package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query("select x.name from Permission x")
    List<String> findAllNames();
    Permission findByDbid(int dbid);
    Permission findByName(String name);
    Permission findFirstByNameStartingWith(String name);
}
