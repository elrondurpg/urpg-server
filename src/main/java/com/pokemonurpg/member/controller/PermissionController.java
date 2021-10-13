package com.pokemonurpg.member.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.member.input.PermissionInputDto;
import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.service.PermissionService;
import com.pokemonurpg.security.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pokemonurpg.strings.PermissionNames.READ_PERMISSION_PERMISSION;
import static com.pokemonurpg.strings.PermissionNames.WRITE_PERMISSION_PERMISSION;

@RestController
@RequestMapping("/permission")
@CrossOrigin
@Validated
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @AllowAuthorized(permission = READ_PERMISSION_PERMISSION)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return permissionService.findAllNames();
    }

    @AllowAuthorized(permission = READ_PERMISSION_PERMISSION)
    @GetMapping(path="/{name}")
    public @ResponseBody
    Permission findByName(@PathVariable("name") String name) {
        return permissionService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = WRITE_PERMISSION_PERMISSION)
    @PostMapping
    public @ResponseBody
    Permission create(@Valid @RequestBody PermissionInputDto input) {
        return permissionService.create(input);
    }

    @AllowAuthorized(permission = WRITE_PERMISSION_PERMISSION)
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Permission update(@Valid @RequestBody PermissionInputDto input, @PathVariable int dbid) {
        return permissionService.update(input, dbid);
    }
}
