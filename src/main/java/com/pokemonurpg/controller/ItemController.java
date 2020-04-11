package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.ItemDto;
import com.pokemonurpg.object.Item;
import com.pokemonurpg.service.ItemService;
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

    @GetMapping(path="/{name}")
    public @ResponseBody
    RestResponse getItemByName(@PathVariable("name") String name) {
        try {
            Item item = itemService.findByName(name);
            if (item != null) {
                return new RestResponse(200, item);
            }
            else return new RestResponse(404, null);
        } catch (IllegalStateException e) {
            return new RestResponse(400, null);
        }
    }
}
