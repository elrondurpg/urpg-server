package com.pokemonurpg.repository;

import com.pokemonurpg.object.trainer.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select x.name from Role x")
    List<Object> findAllNames();
    Role findByDbid(int dbid);
    Role findByName(String name);
    List<Role> findByNameStartingWith(String name);
}
