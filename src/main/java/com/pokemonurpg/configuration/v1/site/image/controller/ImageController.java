package com.pokemonurpg.configuration.v1.site.image.controller;

import com.pokemonurpg.configuration.v1.site.image.input.ImageInputDto;
import com.pokemonurpg.configuration.v1.site.image.service.ImageService;
import com.pokemonurpg.security.annotation.AllowAuthorized;
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

    @AllowAuthorized(permission = "Write Image")
    @PostMapping
    public @ResponseBody
    ImageInputDto create(@Valid @RequestBody ImageInputDto input) {
        return imageService.create(input);
    }
}
