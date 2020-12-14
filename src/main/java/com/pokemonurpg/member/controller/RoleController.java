package com.pokemonurpg.member.controller;

import com.pokemonurpg.core.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.member.input.RoleInputDto;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return roleService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Role findByName(@PathVariable("name") String name) {
        return roleService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    Role create(@Valid @RequestBody RoleInputDto input) {
        return roleService.create(input);
    }

    @Authorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Role update(@Valid @RequestBody RoleInputDto input, @PathVariable int dbid) {
        return roleService.update(input, dbid);
    }
}
