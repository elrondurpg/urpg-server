package com.pokemonurpg.gym.controller;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.ChampionOwnershipTermInputDto;
import com.pokemonurpg.gym.models.ChampionOwnershipTerm;
import com.pokemonurpg.gym.service.ChampionOwnershipTermService;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_DELETE;

@RestController
@RequestMapping("/championOwnershipTerm")
@CrossOrigin
@Validated
public class ChampionOwnershipTermController {

    @Resource
    private ChampionOwnershipTermService championOwnershipTermService;

    @AllowAll
    @GetMapping(path="/{dbid}")
    public @ResponseBody
    ChampionOwnershipTerm findByDbid(@PathVariable("dbid") Integer dbid) {
        return championOwnershipTermService.findByDbid(dbid);
    }

    @AllowAll
    @GetMapping
    public @ResponseBody
    ChampionOwnershipTerm findBySlotAndOwnerAndOpenDate(@RequestParam(required = true) String champion, @RequestParam(required = true) String owner, @RequestParam(required = true) Date openDate) {
        return championOwnershipTermService.findBySlotAndOwnerAndOpenDate(champion, owner, openDate);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Champion")
    @PostMapping
    public @ResponseBody
    ChampionOwnershipTerm create(@Valid @RequestBody ChampionOwnershipTermInputDto input) {
        return championOwnershipTermService.create(input);
    }

    @AllowAuthorized(permission = "Write Champion")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ChampionOwnershipTerm update(@Valid @RequestBody ChampionOwnershipTermInputDto input, @PathVariable int dbid) {
        return championOwnershipTermService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Champion")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            championOwnershipTermService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}