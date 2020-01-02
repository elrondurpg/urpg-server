package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.pokemon.ORASContestMoveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orasContestMoveType")
@CrossOrigin
public class ORASContestMoveTypeController {

    private ORASContestMoveTypeService orasContestMoveTypeService;

    @Autowired
    public ORASContestMoveTypeController(ORASContestMoveTypeService orasContestMoveTypeService) {
        this.orasContestMoveTypeService = orasContestMoveTypeService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllORASContestMoveTypes() {
        return new RestResponse(200, orasContestMoveTypeService.findAllFull());
    }
}
