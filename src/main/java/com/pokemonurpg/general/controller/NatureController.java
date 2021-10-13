package com.pokemonurpg.general.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.general.input.NatureInputDto;
import com.pokemonurpg.general.models.Nature;
import com.pokemonurpg.general.service.NatureService;
import com.pokemonurpg.security.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/nature")
@CrossOrigin
@Validated
public class NatureController {

    @Resource
    private NatureService natureService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return natureService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    Nature findByName(@PathVariable("name") String name) {
        return natureService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write General")
    @PostMapping
    public @ResponseBody
    Nature create(@Valid @RequestBody NatureInputDto input) {
        return natureService.create(input);
    }

    @AllowAuthorized(permission = "Write General")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Nature update(@Valid @RequestBody NatureInputDto input, @PathVariable int dbid) {
        return natureService.update(input, dbid);
    }
}
