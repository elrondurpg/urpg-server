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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v1.item.input.ItemInputDto;
import com.pokemonurpg.configuration.v1.item.service.ItemService;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.entities.v1.item.Item;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;

@RestController
@RequestMapping("/item")
@CrossOrigin
@Validated
public class ItemController {

    @Resource
    private ItemService itemService;

    // @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    // @GetMapping
    // public @ResponseBody
    // List<String> findNamesBy(@RequestParam(required = false) String type) {
    //     return itemService.findNamesBy(type);
    // }

    // @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    // @GetMapping(path="/type/{types}")
    // public @ResponseBody
    // List<Item> findByTypeIn(@PathVariable("types") List<String> types) {
    //     return itemService.findByTypeIn(types);
    // }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    Item findByName(@PathVariable("name") String name) {
        return itemService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Item")
    @PostMapping
    public @ResponseBody
    Item create(@Valid @RequestBody ItemInputDto input) {
        return itemService.create(input);
    }

    @AllowAuthorized(permission = "Write Item")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Item update(@Valid @RequestBody ItemInputDto input, @PathVariable int dbid) {
        return itemService.update(input, dbid);
    }
}
