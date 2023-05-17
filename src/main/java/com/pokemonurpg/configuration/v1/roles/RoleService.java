package com.pokemonurpg.configuration.v1.roles;

import com.pokemonurpg.infrastructure.data.RoleRepository;
import com.pokemonurpg.configuration.v1.permissions.PermissionService;
import com.pokemonurpg.entities.Permission;
import com.pokemonurpg.entities.Role;
import com.pokemonurpg.lib.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class RoleService implements NamedObjectService<Role> {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private PermissionService permissionService;

    public List<String> findAllNames() {
        return roleRepository.findAllNames();
    }

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

    @Override
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
