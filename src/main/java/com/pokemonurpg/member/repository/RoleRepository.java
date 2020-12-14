package com.pokemonurpg.member.repository;

import com.pokemonurpg.member.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select x.name from Role x")
    List<String> findAllNames();
    Role findByDbid(int dbid);
    Role findByName(String name);
    Role findFirstByNameStartingWith(String name);
}
