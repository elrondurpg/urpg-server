package com.pokemonurpg.configuration.v1.parkranks;

import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.ParkRank;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/park-ranks")
@CrossOrigin
@Validated
public class ParkRankController {

    @Resource
    private ParkRankService parkRankService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return parkRankService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ParkRank findByName(@PathVariable("name") String name) {
        return parkRankService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Park")
    @PostMapping
    public @ResponseBody
    ParkRank create(@Valid @RequestBody ParkRankRequest input) {
        return parkRankService.create(input);
    }

    @AllowAuthorized(permission = "Write Park")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ParkRank update(@Valid @RequestBody ParkRankRequest input, @PathVariable int dbid) {
        return parkRankService.update(input, dbid);
    }
}
