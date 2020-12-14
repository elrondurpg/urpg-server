package com.pokemonurpg.attack.controller;

import com.pokemonurpg.attack.models.AttackCategory;
import com.pokemonurpg.attack.input.AttackCategoryInputDto;
import com.pokemonurpg.attack.service.AttackCategoryService;
import com.pokemonurpg.core.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/attackcategory")
@CrossOrigin
@Validated
public class AttackCategoryController {

    @Resource
    private AttackCategoryService attackCategoryService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return attackCategoryService.findAllNames();
    }

    @GetMapping(path = "/{name}")
    public @ResponseBody
    AttackCategory findByName(@PathVariable("name") String name) {
        return attackCategoryService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Attack")
    @PostMapping
    public @ResponseBody
    AttackCategory create(@Valid @RequestBody AttackCategoryInputDto input) {
        return attackCategoryService.create(input);
    }

    @Authorized(permission = "Write Attack")
    @PutMapping(path = "/{dbid}")
    public @ResponseBody
    AttackCategory update(@Valid @RequestBody AttackCategoryInputDto input, @PathVariable int dbid) {
        return attackCategoryService.update(input, dbid);
    }
}
