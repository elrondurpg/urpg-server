package com.pokemonurpg.configuration.v1.contestgenerations;

import com.pokemonurpg.entities.v1.ContestType;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contestType")
@CrossOrigin
@Validated
public class ContestTypeController {

    @Resource
    private ContestTypeService contestTypeService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestTypeService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ContestType findByName(@PathVariable("name") String name) {
        return contestTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    ContestType create(@Valid @RequestBody ContestTypeInputDto input) {
        return contestTypeService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ContestType update(@Valid @RequestBody ContestTypeInputDto input, @PathVariable int dbid) {
        return contestTypeService.update(input, dbid);
    }
}
