package com.pokemonurpg.repository;

import com.pokemonurpg.object.MemberRole;
import com.pokemonurpg.object.MemberRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, MemberRoleKey> {
}
