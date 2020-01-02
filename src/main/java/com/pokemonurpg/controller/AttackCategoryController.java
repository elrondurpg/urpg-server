package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.pokemon.AttackCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attackcategory")
@CrossOrigin
public class AttackCategoryController {
    private AttackCategoryService attackCategoryService;

    @Autowired
    public AttackCategoryController(AttackCategoryService attackCategoryService) {
        this.attackCategoryService = attackCategoryService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllAttackCategories() {
        return new RestResponse(200, attackCategoryService.findAll());
    }
}
