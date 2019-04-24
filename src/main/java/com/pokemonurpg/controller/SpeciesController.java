package com.pokemonurpg.controller;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class SpeciesController {

    @Autowired
    private SpeciesService service;

    @GetMapping(value = "/{name}")
    public List<Species> findByName(@PathVariable("name") String name) {
        return service.findByName(name);
    }

}
