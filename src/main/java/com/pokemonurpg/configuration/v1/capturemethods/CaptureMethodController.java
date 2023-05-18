package com.pokemonurpg.configuration.v1.capturemethods;

import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.CaptureMethod;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/capture-methods")
@CrossOrigin
@Validated
public class CaptureMethodController {

    @Resource
    private CaptureMethodService captureMethodService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return captureMethodService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    CaptureMethod findByName(@PathVariable("name") String name) {
        return captureMethodService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write General")
    @PostMapping
    public @ResponseBody
    CaptureMethod create(@Valid @RequestBody CaptureMethodRequest input) {
        return captureMethodService.create(input);
    }

    @AllowAuthorized(permission = "Write General")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    CaptureMethod update(@Valid @RequestBody CaptureMethodRequest input, @PathVariable int dbid) {
        return captureMethodService.update(input, dbid);
    }
}
