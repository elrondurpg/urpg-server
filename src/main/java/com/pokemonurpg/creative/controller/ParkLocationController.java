package com.pokemonurpg.creative.controller;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.creative.input.ParkLocationInputDto;
import com.pokemonurpg.creative.models.ParkLocation;
import com.pokemonurpg.creative.service.ParkLocationService;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parkLocation")
@CrossOrigin
@Validated
public class ParkLocationController {

    @Resource
    private ParkLocationService parkLocationService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return parkLocationService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    ParkLocation findByName(@PathVariable("name") String name) {
        return parkLocationService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Park")
    @PostMapping
    public @ResponseBody
    ParkLocation create(@Valid @RequestBody ParkLocationInputDto input) {
        return parkLocationService.create(input);
    }

    @AllowAuthorized(permission = "Write Park")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ParkLocation update(@Valid @RequestBody ParkLocationInputDto input, @PathVariable int dbid) {
        return parkLocationService.update(input, dbid);
    }
}
