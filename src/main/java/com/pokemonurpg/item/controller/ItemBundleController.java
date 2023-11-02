package com.pokemonurpg.item.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.item.input.ItemBundleInputDto;
import com.pokemonurpg.item.models.ItemBundle;
import com.pokemonurpg.item.service.ItemBundleService;
import com.pokemonurpg.security.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-server/itemBundle")
@CrossOrigin
@Validated
public class ItemBundleController {

    @Resource
    private ItemBundleService itemBundleService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return itemBundleService.findAllNames();
    }

    @AllowAll
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
