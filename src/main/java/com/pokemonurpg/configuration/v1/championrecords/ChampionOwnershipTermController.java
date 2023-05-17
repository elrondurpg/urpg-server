package com.pokemonurpg.configuration.v1.championrecords;

import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.ChampionOwnershipTerm;
import com.pokemonurpg.login.annotation.AllowAll;
import com.pokemonurpg.login.annotation.AllowAuthorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pokemonurpg.lib.strings.ErrorStrings.ERROR_ON_DELETE;

@RestController
@RequestMapping("/championOwnershipTerm")
@CrossOrigin
@Validated
public class ChampionOwnershipTermController {

    @Resource
    private ChampionOwnershipTermService championOwnershipTermService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<ChampionOwnershipTerm> findAll() {
        return championOwnershipTermService.findAll();
    }

    @AllowAll
    @GetMapping(path="/{dbid}")
    public @ResponseBody
    ChampionOwnershipTerm findByDbid(@PathVariable("dbid") Integer dbid) {
        return championOwnershipTermService.findByDbid(dbid);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    ChampionOwnershipTerm create(@Valid @RequestBody ChampionOwnershipTermInputDto input) {
        return championOwnershipTermService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ChampionOwnershipTerm update(@Valid @RequestBody ChampionOwnershipTermInputDto input, @PathVariable int dbid) {
        return championOwnershipTermService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
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
