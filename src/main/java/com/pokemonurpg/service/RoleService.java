package com.pokemonurpg.service;

import com.pokemonurpg.dto.security.RoleDto;
import com.pokemonurpg.dto.security.RoleInputDto;
import com.pokemonurpg.dto.security.RolePermissionInputDto;
import com.pokemonurpg.object.Permission;
import com.pokemonurpg.object.Role;
import com.pokemonurpg.object.RolePermission;
import com.pokemonurpg.repository.PermissionRepository;
import com.pokemonurpg.repository.RolePermissionRepository;
import com.pokemonurpg.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    private RolePermissionService rolePermissionService;
    private RolePermissionRepository rolePermissionRepository;
    private PermissionRepository permissionRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, RolePermissionService rolePermissionService, RolePermissionRepository rolePermissionRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.rolePermissionService = rolePermissionService;
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<Object> findAll() {
        return roleRepository.findAllNames();
    }

    public Role findByDbid(int dbid) {
        return roleRepository.findByDbid(dbid);
    }

    public RoleDto findByName(String name) {
        Role role = roleRepository.findByName(name);
        RoleDto dto = null;
        if (role != null) {
            dto = new RoleDto(role);
        }
        else {
            List<Role> results = roleRepository.findByNameStartingWith(name);
            if (!results.isEmpty()) {
                dto = new RoleDto(results.get(0));
            }
            else return null;
        }

        dto.setPermissions(buildDtoPermissions(role.getDbid()));

        return dto;
    }

    public List<String> buildDtoPermissions(int roleDbid) {
        List<RolePermission> permissions = rolePermissionRepository.findByIdRoleDbid(roleDbid);
        List<String> dtoPermissions = new ArrayList<>();
        for (RolePermission rolePermission : permissions) {
            int dbid = rolePermission.getId().getPermissionDbid();
            Permission permission = permissionRepository.findByDbid(dbid);
            if (permission != null) {
                dtoPermissions.add(permission.getName());
            }
        }
        return dtoPermissions;
    }

    public Errors createRole(RoleInputDto input) {
        Errors errors = validateRoleCreate(input);
        if (!errors.hasErrors()) {
            Role role = new Role(input);
            roleRepository.save(role);

            Role savedRole = roleRepository.findByName(input.getName());
            int dbid = savedRole.getDbid();

            rolePermissionService.createAll(dbid, input.getPermissions());
        }
        return errors;
    }

    public Errors updateRole(RoleInputDto input) {
        Errors errors = validateRoleUpdate(input);
        if (!errors.hasErrors()) {
            Role existingRole = roleRepository.findByName(input.getName());
            if (input.getName() != null) {
                existingRole.setName(input.getName());
            }

            roleRepository.save(existingRole);
            int dbid = existingRole.getDbid();

            rolePermissionService.updateAll(dbid, input.getPermissions());
        }
        return errors;
    }

    public Errors validateRoleCreate(RoleInputDto input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Role existingRecord = roleRepository.findByName(input.getName());
        if (existingRecord == null) {
            if (input.getName() == null || input.getName().length() < 3 || input.getName().length() > 17) {
                errors.reject("Role name " + input.getName() + " is invalid.");
            }

            if (input.getPermissions() != null) {
                for (RolePermissionInputDto rolePermissionInputDto : input.getPermissions()) {
                    if (rolePermissionInputDto.getName() != null && permissionRepository.findByName(rolePermissionInputDto.getName()) == null) {
                        errors.reject("Permission name " + rolePermissionInputDto.getName() + " is invalid.");
                    }
                }
            }
        }
        else {
            errors.reject("Role " + input.getName() + " already exists.");
        }

        return errors;
    }

    public Errors validateRoleUpdate(RoleInputDto input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Role existingRecord = roleRepository.findByName(input.getName());
        if (existingRecord != null) {
            if (input.getName() != null && (input.getName().length() < 3 || input.getName().length() > 17)) {
                errors.reject("Role name " + input.getName() + " is invalid.");
            }

            if (input.getPermissions() != null) {
                for (RolePermissionInputDto rolePermissionInputDto : input.getPermissions()) {
                    if (rolePermissionInputDto.getName() != null && permissionRepository.findByName(rolePermissionInputDto.getName()) == null) {
                        errors.reject("Permission name " + rolePermissionInputDto.getName() + " is invalid.");
                    }
                }
            }
        }
        else {
            errors.reject("Role " + input.getName() + " doesn't exist.");
        }

        return errors;
    }

    public List<Role> findByNameStartingWith(String name) {
        return roleRepository.findByNameStartingWith(name);
    }
}
