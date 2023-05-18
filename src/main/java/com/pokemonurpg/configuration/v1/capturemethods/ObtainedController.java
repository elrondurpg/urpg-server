package com.pokemonurpg.configuration.v1.capturemethods;

import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.Obtained;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/obtained")
@CrossOrigin
@Validated
public class ObtainedController {

    @Resource
    private ObtainedService obtainedService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return obtainedService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    Obtained findByName(@PathVariable("name") String name) {
        return obtainedService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write General")
    @PostMapping
    public @ResponseBody
    Obtained create(@Valid @RequestBody ObtainedInputDto input) {
        return obtainedService.create(input);
    }

    @AllowAuthorized(permission = "Write General")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Obtained update(@Valid @RequestBody ObtainedInputDto input, @PathVariable int dbid) {
        return obtainedService.update(input, dbid);
    }
}
