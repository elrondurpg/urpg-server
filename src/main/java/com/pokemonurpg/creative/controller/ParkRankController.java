package com.pokemonurpg.creative.controller;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.creative.input.ParkRankInputDto;
import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.service.ParkRankService;
import com.pokemonurpg.core.security.annotation.Authorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parkRank")
@CrossOrigin
@Validated
public class ParkRankController {

    @Resource
    private ParkRankService parkRankService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return parkRankService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    ParkRank findByName(@PathVariable("name") String name) {
        return parkRankService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Park")
    @PostMapping
    public @ResponseBody
    ParkRank create(@Valid @RequestBody ParkRankInputDto input) {
        return parkRankService.create(input);
    }

    @Authorized(permission = "Write Park")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ParkRank update(@Valid @RequestBody ParkRankInputDto input, @PathVariable int dbid) {
        return parkRankService.update(input, dbid);
    }
}
