package com.pokemonurpg.general.controller;

import com.pokemonurpg.core.security.annotation.Authorized;
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

    @GetMapping(path="/all")
    public @ResponseBody
    List<Section> findAll() {
        return sectionService.findAll();
    }

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return sectionService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Section findByName(@PathVariable("name") String name) {
        return sectionService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write General")
    @PostMapping
    public @ResponseBody
    Section create(@Valid @RequestBody SectionInputDto input) {
        return sectionService.create(input);
    }

    @Authorized(permission = "Write General")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Section update(@Valid @RequestBody SectionInputDto input, @PathVariable int dbid) {
        return sectionService.update(input, dbid);
    }
}
