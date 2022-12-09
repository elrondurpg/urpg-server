package com.pokemonurpg.configuration.v1.site.image.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v1.site.image.input.ImageFolderInputDto;
import com.pokemonurpg.configuration.v1.site.image.service.ImageFolderService;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.entities.v1.site.ImageFolder;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;

@RestController
@RequestMapping("/imageFolder")
@CrossOrigin
@Validated
public class ImageFolderController {

    @Resource
    private ImageFolderService imageFolderService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    ImageFolder findByName(@PathVariable("name") String name) {
        return imageFolderService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Image")
    @PostMapping
    public @ResponseBody
    ImageFolder create(@Valid @RequestBody ImageFolderInputDto input) {
        return imageFolderService.create(input);
    }

    @AllowAuthorized(permission = "Write Image")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ImageFolder update(@Valid @RequestBody ImageFolderInputDto input, @PathVariable int dbid) {
        return imageFolderService.update(input, dbid);
    }
}
