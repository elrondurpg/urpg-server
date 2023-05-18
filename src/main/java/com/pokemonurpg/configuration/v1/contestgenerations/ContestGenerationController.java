package com.pokemonurpg.configuration.v1.contestgenerations;

import com.pokemonurpg.entities.v1.ContestGeneration;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/contest-generations")
@CrossOrigin
@Validated
public class ContestGenerationController {

    @Resource
    private ContestGenerationService contestGenerationService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestGenerationService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ContestGeneration findByName(@PathVariable("name") String name) {
        return contestGenerationService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    ContestGeneration create(@Valid @RequestBody ContestGenerationRequest input) {
        return contestGenerationService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ContestGeneration update(@Valid @RequestBody ContestGenerationRequest input, @PathVariable int dbid) {
        return contestGenerationService.update(input, dbid);
    }
}
