package com.pokemonurpg.configuration.v1.member.role;

import static com.pokemonurpg.strings.PermissionNames.WRITE_ROLE_PERMISSION;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.converter.json.MappingJacksonValue;
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

import com.pokemonurpg.View;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.entities.v1.member.Role;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.security.service.AuthorizationService;

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
