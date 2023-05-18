package com.pokemonurpg.configuration.v1.gyms;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.Gym;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pokemonurpg.lib.v1.strings.ErrorStrings.ERROR_ON_DELETE;

@RestController
@RequestMapping("/urpg-configuration/v1/gyms")
@CrossOrigin
@Validated
public class GymController {

    @Resource
    private GymService gymService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAll() {
        return gymService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Gym findByName(@PathVariable("name") String name) {
        return gymService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Gym create(@Valid @RequestBody GymRequest input) {
        return gymService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Gym update(@Valid @RequestBody GymRequest input, @PathVariable int dbid) {
        return gymService.update(dbid, input);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            gymService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
