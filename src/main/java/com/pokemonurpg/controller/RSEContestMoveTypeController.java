package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.RSEContestMoveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rseContestMoveType")
@CrossOrigin
public class RSEContestMoveTypeController {

    private RSEContestMoveTypeService rseContestMoveTypeService;

    @Autowired
    public RSEContestMoveTypeController(RSEContestMoveTypeService rseContestMoveTypeService) {
        this.rseContestMoveTypeService = rseContestMoveTypeService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllRSEContestMoveTypes() {
        return new RestResponse(200, rseContestMoveTypeService.findAllFull());
    }
}
