package com.pokemonurpg.service;

import com.pokemonurpg.object.Permission;
import com.pokemonurpg.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.List;

@Service
public class PermissionService {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Object> findAll() {
        return permissionRepository.findAllNames();
    }

    public Permission findByDbid(int dbid) {
        return permissionRepository.findByDbid(dbid);
    }

    public Permission findByName(String name) {
        return permissionRepository.findByName(name);
    }

    public List<Permission> findByNameStartingWith(String name) {
        return permissionRepository.findByNameStartingWith(name);
    }

    public Errors createPermission(String input) {
        Errors errors = validatePermissionCreate(input);
        if (!errors.hasErrors()) {
            Permission permission = new Permission(input);
            permissionRepository.save(permission);
        }
        return errors;
    }

    public Errors validatePermissionCreate(String input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Permission existingRecord = permissionRepository.findByName(input);
        if (existingRecord == null) {
            if (input == null || input.length() < 3 || input.length() > 17) {
                errors.reject("Permission name " + input + " is invalid.");
            }
        }
        else {
            errors.reject("Permission " + input + " already exists.");
        }

        return errors;
    }
}
