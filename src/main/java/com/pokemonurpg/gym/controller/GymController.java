package com.pokemonurpg.gym.controller;

import com.pokemonurpg.core.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.service.GymService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gym")
@CrossOrigin
@Validated
public class GymController {

    @Resource
    private GymService gymService;

    @GetMapping
    public @ResponseBody
    List<Gym> findAll() {
        return gymService.findAll();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Gym findByName(@PathVariable("name") String name) {
        return gymService.findFirstActiveByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    Gym create(@Valid @RequestBody GymInputDto input) {
        return gymService.create(input);
    }

    @Authorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Gym update(@Valid @RequestBody GymInputDto input, @PathVariable int dbid) {
        return gymService.update(input, dbid);
    }
}