package com.pokemonurpg.service;

import com.pokemonurpg.dto.security.RolePermissionInputDto;
import com.pokemonurpg.object.Permission;
import com.pokemonurpg.object.RolePermission;
import com.pokemonurpg.object.RolePermissionKey;
import com.pokemonurpg.repository.PermissionRepository;
import com.pokemonurpg.repository.RolePermissionRepository;
import com.pokemonurpg.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionService {

    private RolePermissionRepository rolePermissionRepository;
    private PermissionRepository permissionRepository;

    @Autowired
    public RolePermissionService(RolePermissionRepository rolePermissionRepository, PermissionRepository permissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionRepository = permissionRepository;
    }

    public void create(int roleDbid, RolePermissionInputDto input) {
        Permission permission = permissionRepository.findByName(input.getName());
        if (permission != null) {
            RolePermissionKey key = new RolePermissionKey(roleDbid, permission.getDbid());
            RolePermission rolePermission = new RolePermission(key);
            rolePermissionRepository.save(rolePermission);
        }
    }

    public void createAll(int roleDbid, List<RolePermissionInputDto> input) {
        for (RolePermissionInputDto record : input) {
            if (!record.isDeleted()) {
                create(roleDbid, record);
            }
        }
    }

    public void update(RolePermission existingRecord, RolePermissionInputDto input) {
        if (input.isDeleted()) {
            rolePermissionRepository.delete(existingRecord);
        }
        else {
            rolePermissionRepository.save(existingRecord);
        }
    }

    public void updateAll(int roleDbid, List<RolePermissionInputDto> input) {
        if (input != null) {
            for (RolePermissionInputDto record : input) {
                Permission permission = permissionRepository.findByName(record.getName());

                RolePermission existingRecord = rolePermissionRepository.findByIdRoleDbidAndIdPermissionDbid(roleDbid, permission.getDbid());
                if (existingRecord != null) {
                    update(existingRecord, record);
                } else if (!record.isDeleted()) {
                    create(roleDbid, record);
                }
            }
        }
    }
}
