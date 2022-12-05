package com.pokemonurpg.configuration.v1.item.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.configuration.v1.item.input.ItemBundleInputDto;
import com.pokemonurpg.configuration.v1.item.models.ItemBundle;
import com.pokemonurpg.configuration.v1.item.service.ItemBundleService;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/itemBundle")
@CrossOrigin
@Validated
public class ItemBundleController {

    @Resource
    private ItemBundleService itemBundleService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return itemBundleService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    ItemBundle findByName(@PathVariable("name") String name) {
        return itemBundleService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Item")
    @PostMapping
    public @ResponseBody
    ItemBundle create(@Valid @RequestBody ItemBundleInputDto input) {
        return itemBundleService.create(input);
    }

    @AllowAuthorized(permission = "Write Item")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ItemBundle update(@Valid @RequestBody ItemBundleInputDto input, @PathVariable int dbid) {
        return itemBundleService.update(input, dbid);
    }
}
