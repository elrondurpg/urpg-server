package com.pokemonurpg.repository;

import com.pokemonurpg.object.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByDbid(int dbid);
}
