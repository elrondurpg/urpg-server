package com.pokemonurpg.configuration.v1.featureflags;

import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.FeatureFlag;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/feature-flags")
@CrossOrigin
@Validated
public class FeatureFlagController {

    @Resource
    private FeatureFlagService featureFlagService;

    @AllowAuthorized(permission = "Write Admin")
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return featureFlagService.findAllNames();
    }

    @AllowAuthorized(permission = "Write Admin")
    @GetMapping(path="/{name}")
    public @ResponseBody
    FeatureFlag findByName(@PathVariable("name") String name) {
        return featureFlagService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Admin")
    @PostMapping
    public @ResponseBody
    FeatureFlag create(@Valid @RequestBody FeatureFlagRequest input) {
        return featureFlagService.create(input);
    }

    @AllowAuthorized(permission = "Write Admin")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    FeatureFlag update(@Valid @RequestBody FeatureFlagRequest input, @PathVariable int dbid) {
        return featureFlagService.update(input, dbid);
    }
}
