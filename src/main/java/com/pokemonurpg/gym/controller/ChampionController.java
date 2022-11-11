package com.pokemonurpg.gym.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.ChampionInputDto;
import com.pokemonurpg.gym.models.Champion;
import com.pokemonurpg.gym.service.ChampionService;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_DELETE;

@RestController
@RequestMapping("/champion")
@CrossOrigin
@Validated
public class ChampionController {

    @Resource
    private ChampionService championService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAll() {
        return championService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Champion findByName(@PathVariable("name") String name) {
        return championService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Champion create(@Valid @RequestBody ChampionInputDto input) {
        return championService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Champion update(@Valid @RequestBody ChampionInputDto input, @PathVariable int dbid) {
        return championService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            championService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
