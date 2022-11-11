package com.pokemonurpg.attack.controller;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.attack.input.AttackInputDto;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.attack.service.AttackService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/attack")
@CrossOrigin
@Validated
public class AttackController {

    @Resource
    private AttackService attackService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return attackService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    Attack findByName(@PathVariable("name") String name) {
        return attackService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Attack")
    @PostMapping
    public @ResponseBody
    Attack create(@Valid @RequestBody AttackInputDto input) {
        return attackService.create(input);
    }

    @AllowAuthorized(permission = "Write Attack")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Attack update(@Valid @RequestBody AttackInputDto input, @PathVariable int dbid) {
        return attackService.update(input, dbid);
    }
}
