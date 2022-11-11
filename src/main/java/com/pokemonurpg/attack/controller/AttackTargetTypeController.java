package com.pokemonurpg.attack.controller;

import com.pokemonurpg.attack.models.AttackTargetType;
import com.pokemonurpg.attack.input.AttackTargetTypeInputDto;
import com.pokemonurpg.attack.service.AttackTargetTypeService;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/attacktargettype")
@CrossOrigin
@Validated
public class AttackTargetTypeController {
    @Resource
    private AttackTargetTypeService attackTargetTypeService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return attackTargetTypeService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path = "/{name}")
    public @ResponseBody
    AttackTargetType findByName(@PathVariable("name") String name) {
        return attackTargetTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Attack")
    @PostMapping
    public @ResponseBody
    AttackTargetType create(@Valid @RequestBody AttackTargetTypeInputDto input) {
        return attackTargetTypeService.create(input);
    }

    @AllowAuthorized(permission = "Write Attack")
    @PutMapping(path = "/{dbid}")
    public @ResponseBody
    AttackTargetType update(@Valid @RequestBody AttackTargetTypeInputDto input, @PathVariable int dbid) {
        return attackTargetTypeService.update(input, dbid);
    }
}
