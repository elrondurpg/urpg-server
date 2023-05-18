package com.pokemonurpg.configuration.v1.attackcategories;

import com.pokemonurpg.entities.v1.AttackCategory;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/attack-categories")
@CrossOrigin
@Validated
public class AttackCategoryController {

    @Resource
    private AttackCategoryService attackCategoryService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return attackCategoryService.findAllNames();
    }

    @AllowAll
    @GetMapping(path = "/{name}")
    public @ResponseBody
    AttackCategory findByName(@PathVariable("name") String name) {
        return attackCategoryService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Attack")
    @PostMapping
    public @ResponseBody
    AttackCategory create(@Valid @RequestBody AttackCategoryRequest input) {
        return attackCategoryService.create(input);
    }

    @AllowAuthorized(permission = "Write Attack")
    @PutMapping(path = "/{dbid}")
    public @ResponseBody
    AttackCategory update(@Valid @RequestBody AttackCategoryRequest input, @PathVariable int dbid) {
        return attackCategoryService.update(input, dbid);
    }
}
