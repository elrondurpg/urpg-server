package com.pokemonurpg.configuration.v1.parklocations;

import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.ParkLocation;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/park-locations")
@CrossOrigin
@Validated
public class ParkLocationController {

    @Resource
    private ParkLocationService parkLocationService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return parkLocationService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ParkLocation findByName(@PathVariable("name") String name) {
        return parkLocationService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Park")
    @PostMapping
    public @ResponseBody
    ParkLocation create(@Valid @RequestBody ParkLocationRequest input) {
        return parkLocationService.create(input);
    }

    @AllowAuthorized(permission = "Write Park")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ParkLocation update(@Valid @RequestBody ParkLocationRequest input, @PathVariable int dbid) {
        return parkLocationService.update(input, dbid);
    }
}
