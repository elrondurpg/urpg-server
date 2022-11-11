package com.pokemonurpg.general.controller;

import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.general.input.SectionInputDto;
import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.general.service.SectionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/section")
@CrossOrigin
@Validated
public class SectionController {

    @Resource
    private SectionService sectionService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/all")
    public @ResponseBody
    List<Section> findAll() {
        return sectionService.findAll();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return sectionService.findAllNames();
    }

	@CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    Section findByName(@PathVariable("name") String name) {
        return sectionService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write General")
    @PostMapping
    public @ResponseBody
    Section create(@Valid @RequestBody SectionInputDto input) {
        return sectionService.create(input);
    }

    @AllowAuthorized(permission = "Write General")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Section update(@Valid @RequestBody SectionInputDto input, @PathVariable int dbid) {
        return sectionService.update(input, dbid);
    }
}
