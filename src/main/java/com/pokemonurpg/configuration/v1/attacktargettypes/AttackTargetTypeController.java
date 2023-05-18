package com.pokemonurpg.configuration.v1.attacktargettypes;

import com.pokemonurpg.entities.v1.AttackTargetType;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/attack-target-types")
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
    AttackTargetType create(@Valid @RequestBody AttackTargetTypeRequest input) {
        return attackTargetTypeService.create(input);
    }

    @AllowAuthorized(permission = "Write Attack")
    @PutMapping(path = "/{dbid}")
    public @ResponseBody
    AttackTargetType update(@Valid @RequestBody AttackTargetTypeRequest input, @PathVariable int dbid) {
        return attackTargetTypeService.update(input, dbid);
    }
}
