package com.pokemonurpg.member.service;

import com.pokemonurpg.member.input.RolePermissionInputDto;
import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.input.RoleInputDto;
import com.pokemonurpg.member.repository.RoleRepository;
import com.pokemonurpg.core.service.NamedObjectService;
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
        Role role = roleRepository.findByName(name);
        if (role == null && name != null) {
            return roleRepository.findFirstByNameStartingWith(name);
        }
        else return role;
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
