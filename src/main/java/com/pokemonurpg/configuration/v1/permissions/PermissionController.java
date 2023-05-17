package com.pokemonurpg.configuration.v1.permissions;

import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.Permission;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pokemonurpg.lib.strings.PermissionNames.READ_PERMISSION_PERMISSION;
import static com.pokemonurpg.lib.strings.PermissionNames.WRITE_PERMISSION_PERMISSION;

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
