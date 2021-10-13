package com.pokemonurpg.species.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.species.input.TypeInputDto;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.species.service.TypeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/type")
@CrossOrigin
@Validated
public class TypeController {
    @Resource
    private TypeService typeService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return typeService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Type findByName(@PathVariable("name") String name) {
        return typeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Species")
    @PostMapping
    public @ResponseBody
    Type create(@Valid @RequestBody TypeInputDto input) {
        return typeService.create(input);
    }

    @AllowAuthorized(permission = "Write Species")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Type update(@Valid @RequestBody TypeInputDto input, @PathVariable int dbid) {
        return typeService.update(input, dbid);
    }
}
