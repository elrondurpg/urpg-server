package com.pokemonurpg.configuration.v1.member.permission;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.entities.v1.member.Permission;
import com.pokemonurpg.entities.v1.member.PermissionRepository;

@Service
public class PermissionService {

    @Resource
    private PermissionRepository permissionRepository;

    public Permission findByDbid(int dbid) {
        return permissionRepository.findByDbid(dbid);
    }

    public Permission findByName(String name) {
        Permission permission = findByNameExact(name);
        if (permission == null && name != null) {
            return permissionRepository.findFirstByNameStartingWith(name);
        }
        else return permission;
    }

    public Permission findByNameExact(String name) {
        return permissionRepository.findByName(name);
    }

    public Permission create(PermissionInputDto input) {
        Permission permission = new Permission(input);
        permissionRepository.save(permission);
        return permission;
    }

    public Permission update(PermissionInputDto input, int dbid) {
        Permission permission = permissionRepository.findByDbid(dbid);
        if (permission != null) {
            permission.update(input);
            permissionRepository.save(permission);
        }
        return permission;
    }
}
