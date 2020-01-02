package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.trainer.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllItems() {
        return new RestResponse(200, itemService.findAll());
    }
}
