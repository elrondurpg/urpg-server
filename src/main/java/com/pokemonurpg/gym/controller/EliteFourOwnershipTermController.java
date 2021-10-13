package com.pokemonurpg.gym.controller;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.gym.models.EliteFourOwnershipTerm;
import com.pokemonurpg.gym.service.EliteFourOwnershipTermService;
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
@RequestMapping("/eliteFourOwnershipTerm")
@CrossOrigin
@Validated
public class EliteFourOwnershipTermController {

    @Resource
    private EliteFourOwnershipTermService eliteFourOwnershipTermService;

    @AllowAll
    @GetMapping(path="/{dbid}")
    public @ResponseBody
    EliteFourOwnershipTerm findByDbid(@PathVariable("dbid") Integer dbid) {
        return eliteFourOwnershipTermService.findByDbid(dbid);
    }

    @AllowAll
    @GetMapping
    public @ResponseBody
    EliteFourOwnershipTerm findBySlotAndOwnerAndOpenDate(@RequestParam(required = true) String eliteFour, @RequestParam(required = true) String owner, @RequestParam(required = true) Date openDate) {
        return eliteFourOwnershipTermService.findBySlotAndOwnerAndOpenDate(eliteFour, owner, openDate);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write EliteFour")
    @PostMapping
    public @ResponseBody
    EliteFourOwnershipTerm create(@Valid @RequestBody EliteFourOwnershipTermInputDto input) {
        return eliteFourOwnershipTermService.create(input);
    }

    @AllowAuthorized(permission = "Write EliteFour")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    EliteFourOwnershipTerm update(@Valid @RequestBody EliteFourOwnershipTermInputDto input, @PathVariable int dbid) {
        return eliteFourOwnershipTermService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete EliteFour")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            eliteFourOwnershipTermService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
