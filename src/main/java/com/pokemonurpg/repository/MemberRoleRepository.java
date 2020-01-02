package com.pokemonurpg.repository;

import com.pokemonurpg.object.trainer.MemberRole;
import com.pokemonurpg.object.trainer.MemberRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRoleRepository extends JpaRepository<MemberRole, MemberRoleKey> {
    List<MemberRole> findByIdMemberDbid(int memberDbid);
    MemberRole findByIdMemberDbidAndIdRoleDbid(int memberDbid, int roleDbid);
}
