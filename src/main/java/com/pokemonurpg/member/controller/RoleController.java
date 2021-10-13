package com.pokemonurpg.member.controller;

import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.member.input.RoleInputDto;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pokemonurpg.strings.PermissionNames.READ_ROLE_PERMISSION;
import static com.pokemonurpg.strings.PermissionNames.WRITE_ROLE_PERMISSION;

@RestController
@RequestMapping("/role")
@CrossOrigin
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    @AllowAuthorized(permission = READ_ROLE_PERMISSION)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return roleService.findAllNames();
    }

    @AllowAuthorized(permission = READ_ROLE_PERMISSION)
    @GetMapping(path="/{name}")
    public @ResponseBody
    Role findByName(@PathVariable("name") String name) {
        return roleService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = WRITE_ROLE_PERMISSION)
    @PostMapping
    public @ResponseBody
    Role create(@Valid @RequestBody RoleInputDto input) {
        return roleService.create(input);
    }

    @AllowAuthorized(permission = WRITE_ROLE_PERMISSION)
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Role update(@Valid @RequestBody RoleInputDto input, @PathVariable int dbid) {
        return roleService.update(input, dbid);
    }
}
