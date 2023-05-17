package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.AttackTargetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attacktargettype")
@CrossOrigin
public class AttackTargetTypeController {
    private AttackTargetTypeService attackTargetTypeService;

    @Autowired
    public AttackTargetTypeController(AttackTargetTypeService attackTargetTypeService) {
        this.attackTargetTypeService = attackTargetTypeService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllAttackTargetTypes() {
        return new RestResponse(200, attackTargetTypeService.findAll());
    }
}
