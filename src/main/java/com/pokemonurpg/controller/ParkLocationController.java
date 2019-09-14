package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.ParkLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parklocation")
@CrossOrigin
public class ParkLocationController {

    private ParkLocationService parkLocationService;

    @Autowired
    public ParkLocationController(ParkLocationService parkLocationService) {
        this.parkLocationService = parkLocationService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllSpecies() {
        return new RestResponse(200, parkLocationService.findAll());
    }
}
