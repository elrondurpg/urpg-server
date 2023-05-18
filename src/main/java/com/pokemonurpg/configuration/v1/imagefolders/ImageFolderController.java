package com.pokemonurpg.configuration.v1.imagefolders;

import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.ImageFolder;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/image-folders")
@CrossOrigin
@Validated
public class ImageFolderController {

    @Resource
    private ImageFolderService imageFolderService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return imageFolderService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ImageFolder findByName(@PathVariable("name") String name) {
        return imageFolderService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Image")
    @PostMapping
    public @ResponseBody
    ImageFolder create(@Valid @RequestBody ImageFolderRequest input) {
        return imageFolderService.create(input);
    }

    @AllowAuthorized(permission = "Write Image")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ImageFolder update(@Valid @RequestBody ImageFolderRequest input, @PathVariable int dbid) {
        return imageFolderService.update(input, dbid);
    }
}
