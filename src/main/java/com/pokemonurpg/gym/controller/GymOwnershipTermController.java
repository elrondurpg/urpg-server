package com.pokemonurpg.gym.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.GymOwnershipTermInputDto;
import com.pokemonurpg.gym.models.GymOwnershipTerm;
import com.pokemonurpg.gym.service.GymOwnershipTermService;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_DELETE;

@RestController
@RequestMapping("/gymOwnershipTerm")
@CrossOrigin
@Validated
public class GymOwnershipTermController {

    @Resource
    private GymOwnershipTermService gymOwnershipTermService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<GymOwnershipTerm> findAll() {
        return gymOwnershipTermService.findAll();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{dbid}")
    public @ResponseBody
    GymOwnershipTerm findByDbid(@PathVariable("dbid") Integer dbid) {
        return gymOwnershipTermService.findByDbid(dbid);
    }

    // @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    // @GetMapping
    // public @ResponseBody
    // GymOwnershipTerm findByGymAndOwnerAndOpenDate(@RequestParam(required = true) String gym, @RequestParam(required = true) String owner, @RequestParam(required = true) Date openDate) {
    //     return gymOwnershipTermService.findByGymAndOwnerAndOpenDate(gym, owner, openDate);
    // }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    GymOwnershipTerm create(@Valid @RequestBody GymOwnershipTermInputDto input) {
        return gymOwnershipTermService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    GymOwnershipTerm update(@Valid @RequestBody GymOwnershipTermInputDto input, @PathVariable int dbid) {
        return gymOwnershipTermService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            gymOwnershipTermService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
