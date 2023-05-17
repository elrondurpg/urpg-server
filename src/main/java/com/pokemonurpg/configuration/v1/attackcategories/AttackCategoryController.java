package com.pokemonurpg.configuration.v1.attackcategories;

import com.pokemonurpg.entities.AttackCategory;
import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.login.annotation.AllowAll;
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
