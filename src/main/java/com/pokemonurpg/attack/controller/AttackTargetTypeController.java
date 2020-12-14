package com.pokemonurpg.attack.controller;

import com.pokemonurpg.attack.models.AttackTargetType;
import com.pokemonurpg.attack.input.AttackTargetTypeInputDto;
import com.pokemonurpg.attack.service.AttackTargetTypeService;
import com.pokemonurpg.core.security.annotation.Authorized;
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

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return attackTargetTypeService.findAllNames();
    }

    @GetMapping(path = "/{name}")
    public @ResponseBody
    AttackTargetType findByName(@PathVariable("name") String name) {
        return attackTargetTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Attack")
    @PostMapping
    public @ResponseBody
    AttackTargetType create(@Valid @RequestBody AttackTargetTypeInputDto input) {
        return attackTargetTypeService.create(input);
    }

    @Authorized(permission = "Write Attack")
    @PutMapping(path = "/{dbid}")
    public @ResponseBody
    AttackTargetType update(@Valid @RequestBody AttackTargetTypeInputDto input, @PathVariable int dbid) {
        return attackTargetTypeService.update(input, dbid);
    }
}
