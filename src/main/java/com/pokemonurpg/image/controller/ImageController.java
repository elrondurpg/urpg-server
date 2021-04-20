package com.pokemonurpg.image.controller;

import com.pokemonurpg.image.input.ImageInputDto;
import com.pokemonurpg.security.annotation.Authorized;
import com.pokemonurpg.image.service.ImageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/image")
@CrossOrigin
@Validated
public class ImageController {
    
    @Resource
    private ImageService imageService;

    @Authorized(permission = "Write Image")
    @PostMapping
    public @ResponseBody
    ImageInputDto create(@Valid @RequestBody ImageInputDto input) {
        return imageService.create(input);
    }
}
