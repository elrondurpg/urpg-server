package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.DPPContestMoveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dppContestMoveType")
@CrossOrigin
public class DPPContestMoveTypeController {

    private DPPContestMoveTypeService dppContestMoveTypeService;

    @Autowired
    public DPPContestMoveTypeController(DPPContestMoveTypeService dppContestMoveTypeService) {
        this.dppContestMoveTypeService = dppContestMoveTypeService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllDPPContestMoveTypes() {
        return new RestResponse(200, dppContestMoveTypeService.findAllFull());
    }
}
