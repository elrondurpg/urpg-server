package com.pokemonurpg.configuration.v1.featureflags;

import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.Flag;
import com.pokemonurpg.login.annotation.AllowAuthorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flag")
@CrossOrigin
@Validated
public class FlagController {

    @Resource
    private FlagService flagService;

    @AllowAuthorized(permission = "Write Admin")
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return flagService.findAllNames();
    }

    @AllowAuthorized(permission = "Write Admin")
    @GetMapping(path="/{name}")
    public @ResponseBody
    Flag findByName(@PathVariable("name") String name) {
        return flagService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Admin")
    @PostMapping
    public @ResponseBody
    Flag create(@Valid @RequestBody FlagInputDto input) {
        return flagService.create(input);
    }

    @AllowAuthorized(permission = "Write Admin")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Flag update(@Valid @RequestBody FlagInputDto input, @PathVariable int dbid) {
        return flagService.update(input, dbid);
    }
}
