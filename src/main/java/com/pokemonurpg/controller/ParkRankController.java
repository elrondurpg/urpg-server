package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.pokemon.ParkRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkrank")
@CrossOrigin
public class ParkRankController {

    private ParkRankService parkRankService;

    @Autowired
    public ParkRankController(ParkRankService parkRankService) {
        this.parkRankService = parkRankService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllParkRanks() {
        return new RestResponse(200, parkRankService.findAll());
    }
}
