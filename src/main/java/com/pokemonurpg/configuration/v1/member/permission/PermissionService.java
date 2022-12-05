package com.pokemonurpg.configuration.v1.member.permission;

import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.input.PermissionInputDto;
import com.pokemonurpg.member.repository.PermissionRepository;
import com.pokemonurpg.core.service.NamedObjectService;
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
