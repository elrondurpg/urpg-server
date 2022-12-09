package com.pokemonurpg.configuration.v1.member.permission;

import static com.pokemonurpg.strings.PermissionNames.READ_PERMISSION_PERMISSION;
import static com.pokemonurpg.strings.PermissionNames.WRITE_PERMISSION_PERMISSION;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.entities.v1.member.Permission;
import com.pokemonurpg.security.annotation.AllowAuthorized;

@RestController
@RequestMapping("/permission")
@CrossOrigin
@Validated
public class PermissionController {

    @Resource
    private PermissionService permissionService;

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
