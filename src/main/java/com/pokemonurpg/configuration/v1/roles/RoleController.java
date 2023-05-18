package com.pokemonurpg.configuration.v1.roles;

import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.Role;
import com.pokemonurpg.login.v1.AuthorizationService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pokemonurpg.lib.v1.strings.PermissionNames.*;

@RestController
@RequestMapping("/urpg-configuration/v1/roles")
@CrossOrigin
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private AuthorizationService authorizationService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return roleService.findAllNames();
    }

    @AllowAll
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
    Role create(@Valid @RequestBody RoleRequest input) {
        return roleService.create(input);
    }

    @AllowAuthorized(permission = WRITE_ROLE_PERMISSION)
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Role update(@Valid @RequestBody RoleRequest input, @PathVariable int dbid) {
        return roleService.update(input, dbid);
    }
}
