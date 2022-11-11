package com.pokemonurpg.member.controller;

import com.pokemonurpg.View;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.member.input.RoleInputDto;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.service.RoleService;
import com.pokemonurpg.security.service.AuthorizationService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pokemonurpg.strings.PermissionNames.*;

@RestController
@RequestMapping("/role")
@CrossOrigin
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private AuthorizationService authorizationService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return roleService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    MappingJacksonValue findByName(@PathVariable("name") String name) {
        MappingJacksonValue value = new MappingJacksonValue( roleService.findByName(name) );
        if( authorizationService.isAuthorized(WRITE_ROLE_PERMISSION) ) {
            value.setSerializationView( View.MemberView.Secure.class );
        } else {
            value.setSerializationView( View.MemberView.Summary.class );
        }
        return value;
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
