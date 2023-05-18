package com.pokemonurpg.configuration.v1.permissions;

import com.pokemonurpg.infrastructure.v1.data.jpa.PermissionRepository;
import com.pokemonurpg.entities.v1.Permission;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService implements NamedObjectService<Permission> {

    @Resource
    private PermissionRepository permissionRepository;

    public List<String> findAllNames() {
        return permissionRepository.findAllNames();
    }

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

    @Override
    public Permission findByNameExact(String name) {
        return permissionRepository.findByName(name);
    }

    public Permission create(PermissionRequest input) {
        Permission permission = new Permission(input);
        permissionRepository.save(permission);
        return permission;
    }

    public Permission update(PermissionRequest input, int dbid) {
        Permission permission = permissionRepository.findByDbid(dbid);
        if (permission != null) {
            permission.update(input);
            permissionRepository.save(permission);
        }
        return permission;
    }
}
