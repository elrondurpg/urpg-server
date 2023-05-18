package com.pokemonurpg.configuration.v1.attacks;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
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

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return attackService.findAllNames();
    }

    @AllowAll
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
