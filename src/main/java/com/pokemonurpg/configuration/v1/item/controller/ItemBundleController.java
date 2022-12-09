package com.pokemonurpg.configuration.v1.item.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v1.item.input.ItemBundleInputDto;
import com.pokemonurpg.configuration.v1.item.service.ItemBundleService;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.entities.v1.item.ItemBundle;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;

@RestController
@RequestMapping("/itemBundle")
@CrossOrigin
@Validated
public class ItemBundleController {

    @Resource
    private ItemBundleService itemBundleService;

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
