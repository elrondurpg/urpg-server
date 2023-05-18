package com.pokemonurpg.configuration.v1.natures;

import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.Nature;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/natures")
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
    Nature create(@Valid @RequestBody NatureRequest input) {
        return natureService.create(input);
    }

    @AllowAuthorized(permission = "Write General")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Nature update(@Valid @RequestBody NatureRequest input, @PathVariable int dbid) {
        return natureService.update(input, dbid);
    }
}
