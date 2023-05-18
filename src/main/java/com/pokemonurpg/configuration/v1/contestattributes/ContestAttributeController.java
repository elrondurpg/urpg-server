package com.pokemonurpg.configuration.v1.contestattributes;

import com.pokemonurpg.entities.v1.ContestAttribute;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/contest-attributes")
@CrossOrigin
@Validated
public class ContestAttributeController {

    @Resource
    private ContestAttributeService contestAttributeService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestAttributeService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ContestAttribute findByName(@PathVariable("name") String name) {
        return contestAttributeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    ContestAttribute create(@Valid @RequestBody ContestAttributeRequest input) {
        return contestAttributeService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ContestAttribute update(@Valid @RequestBody ContestAttributeRequest input, @PathVariable int dbid) {
        return contestAttributeService.update(input, dbid);
    }
}
