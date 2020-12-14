package com.pokemonurpg.member.controller;

import com.pokemonurpg.core.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.member.input.PermissionInputDto;
import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.service.PermissionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permission")
@CrossOrigin
@Validated
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return permissionService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Permission findByName(@PathVariable("name") String name) {
        return permissionService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Permission")
    @PostMapping
    public @ResponseBody
    Permission create(@Valid @RequestBody PermissionInputDto input) {
        return permissionService.create(input);
    }

    @Authorized(permission = "Write Permission")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Permission update(@Valid @RequestBody PermissionInputDto input, @PathVariable int dbid) {
        return permissionService.update(input, dbid);
    }
}
