package com.pokemonurpg.item.controller;

import com.pokemonurpg.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.item.input.ItemInputDto;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.service.ItemService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
@Validated
public class ItemController {

    @Resource
    private ItemService itemService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return itemService.findAllNames();
    }

    @GetMapping(path="/type/{types}")
    public @ResponseBody
    List<Item> findByTypeIn(@PathVariable("types") List<String> types) {
        return itemService.findByTypeIn(types);
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Item findByName(@PathVariable("name") String name) {
        return itemService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Item")
    @PostMapping
    public @ResponseBody
    Item create(@Valid @RequestBody ItemInputDto input) {
        return itemService.create(input);
    }

    @Authorized(permission = "Write Item")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Item update(@Valid @RequestBody ItemInputDto input, @PathVariable int dbid) {
        return itemService.update(input, dbid);
    }
}
