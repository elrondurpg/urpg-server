package com.pokemonurpg.configuration.v1.types;

import com.pokemonurpg.login.annotation.AllowAll;
import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.Type;
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
	@AllowAll
    public @ResponseBody
    List<String> findAllNames() {
        return typeService.findAllNames();
    }

    @GetMapping(path="/{name}")
	@AllowAll
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
