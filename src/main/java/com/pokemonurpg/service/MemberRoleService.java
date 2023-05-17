package com.pokemonurpg.service;

import com.pokemonurpg.dto.security.MemberRoleInputDto;
import com.pokemonurpg.object.MemberRole;
import com.pokemonurpg.object.MemberRoleKey;
import com.pokemonurpg.object.Role;
import com.pokemonurpg.repository.MemberRoleRepository;
import com.pokemonurpg.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberRoleService {

    private MemberRoleRepository memberRoleRepository;
    private RoleRepository roleRepository;

    @Autowired
    public MemberRoleService(MemberRoleRepository memberRoleRepository, RoleRepository roleRepository) {
        this.memberRoleRepository = memberRoleRepository;
        this.roleRepository = roleRepository;
    }

    public void create(int memberDbid, MemberRoleInputDto input) {
        Role role = roleRepository.findByName(input.getName());
        if (role != null) {
            MemberRoleKey key = new MemberRoleKey(memberDbid, role.getDbid());
            MemberRole memberRole = new MemberRole(key);
            memberRoleRepository.save(memberRole);
        }
    }

    public void update(MemberRole existingRecord, MemberRoleInputDto input) {
        if (input.isDeleted()) {
            memberRoleRepository.delete(existingRecord);
        }
        else {
            memberRoleRepository.save(existingRecord);
        }
    }

    public void updateAll(int memberDbid, List<MemberRoleInputDto> input) {
        if (input != null) {
            for (MemberRoleInputDto record : input) {
                Role role = roleRepository.findByName(record.getName());

                MemberRole existingRecord = memberRoleRepository.findByIdMemberDbidAndIdRoleDbid(memberDbid, role.getDbid());
                if (existingRecord != null) {
                    update(existingRecord, record);
                } else if (!record.isDeleted()) {
                    create(memberDbid, record);
                }
            }
        }
    }
}