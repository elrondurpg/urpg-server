package com.pokemonurpg.attack.controller;

import com.pokemonurpg.attack.models.AttackTargetType;
import com.pokemonurpg.attack.input.AttackTargetTypeInputDto;
import com.pokemonurpg.attack.service.AttackTargetTypeService;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-server/attacktargettype")
@CrossOrigin
@Validated
public class AttackTargetTypeController {
    @Resource
    private AttackTargetTypeService attackTargetTypeService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return attackTargetTypeService.findAllNames();
    }

    @AllowAll
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
