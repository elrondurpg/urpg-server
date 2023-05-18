package com.pokemonurpg.configuration.v1.sections;

import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.Section;
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

    @AllowAll
    @GetMapping(path="/all")
    public @ResponseBody
    List<Section> findAll() {
        return sectionService.findAll();
    }

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return sectionService.findAllNames();
    }

	@AllowAll
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
