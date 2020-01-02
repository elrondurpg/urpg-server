package com.pokemonurpg.repository;

import com.pokemonurpg.object.trainer.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query("select x.name from Permission x")
    List<Object> findAllNames();
    Permission findByDbid(int dbid);
    Permission findByName(String name);
    List<Permission> findByNameStartingWith(String name);
}
