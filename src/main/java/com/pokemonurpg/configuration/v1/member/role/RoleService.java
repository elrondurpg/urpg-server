package com.pokemonurpg.configuration.v1.member.role;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.member.permission.PermissionService;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.entities.v1.member.Permission;
import com.pokemonurpg.entities.v1.member.Role;
import com.pokemonurpg.entities.v1.member.RoleRepository;

@Service
public class RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private PermissionService permissionService;

    public Role findByDbid(int dbid) {
        return roleRepository.findByDbid(dbid);
    }

    public Role findByName(String name) {
        Role role = findByNameExact(name);
        if (role == null && name != null) {
            return roleRepository.findFirstByNameStartingWith(name);
        }
        else return role;
    }

    public Role findByNameExact(String name) {
        return roleRepository.findByName(name);
    }

    public Role create(RoleInputDto input) {
        Role role = new Role(input);
        updateEmbeddedValues(input, role);
        roleRepository.save(role);
        return role;
    }

    public Role update(RoleInputDto input, int dbid) {
        Role role = roleRepository.findByDbid(dbid);
        if (role != null) {
            role.update(input);
            updateEmbeddedValues(input, role);
            roleRepository.save(role);
        }
        return role;
    }

    public void updateEmbeddedValues(RoleInputDto input, Role role) {
        Set<Permission> currentPermissions = role.getPermissions();

        for (RolePermissionInputDto permission : input.getPermissions()) {
            String name = permission.getName();
            Permission permissionObject = permissionService.findByName(name);
            if (permission.getDelete())
                currentPermissions.remove(permissionObject);
            else
                currentPermissions.add(permissionObject);
        }
    }
}
