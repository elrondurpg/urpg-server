package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.pokemon.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
@CrossOrigin
public class TypeController {

    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllTypes() {
        return new RestResponse(200, typeService.findAll());
    }
}
