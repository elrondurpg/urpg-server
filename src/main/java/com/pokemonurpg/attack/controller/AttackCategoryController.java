package com.pokemonurpg.attack.controller;

import com.pokemonurpg.attack.models.AttackCategory;
import com.pokemonurpg.attack.input.AttackCategoryInputDto;
import com.pokemonurpg.attack.service.AttackCategoryService;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
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

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return attackCategoryService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path = "/{name}")
    public @ResponseBody
    AttackCategory findByName(@PathVariable("name") String name) {
        return attackCategoryService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Attack")
    @PostMapping
    public @ResponseBody
    AttackCategory create(@Valid @RequestBody AttackCategoryInputDto input) {
        return attackCategoryService.create(input);
    }

    @AllowAuthorized(permission = "Write Attack")
    @PutMapping(path = "/{dbid}")
    public @ResponseBody
    AttackCategory update(@Valid @RequestBody AttackCategoryInputDto input, @PathVariable int dbid) {
        return attackCategoryService.update(input, dbid);
    }
}
