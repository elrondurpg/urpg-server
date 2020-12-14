package com.pokemonurpg.attack.controller;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.attack.input.AttackInputDto;
import com.pokemonurpg.core.security.annotation.Authorized;
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

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return attackService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Attack findByName(@PathVariable("name") String name) {
        return attackService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Attack")
    @PostMapping
    public @ResponseBody
    Attack create(@Valid @RequestBody AttackInputDto input) {
        return attackService.create(input);
    }

    @Authorized(permission = "Write Attack")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Attack update(@Valid @RequestBody AttackInputDto input, @PathVariable int dbid) {
        return attackService.update(input, dbid);
    }
}
