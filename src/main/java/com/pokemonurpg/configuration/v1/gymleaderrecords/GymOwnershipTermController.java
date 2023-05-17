package com.pokemonurpg.configuration.v1.gymleaderrecords;

import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.GymOwnershipTerm;
import com.pokemonurpg.login.annotation.AllowAll;
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
@RequestMapping("/gymOwnershipTerm")
@CrossOrigin
@Validated
public class GymOwnershipTermController {

    @Resource
    private GymOwnershipTermService gymOwnershipTermService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<GymOwnershipTerm> findAll() {
        return gymOwnershipTermService.findAll();
    }

    @AllowAll
    @GetMapping(path="/{dbid}")
    public @ResponseBody
    GymOwnershipTerm findByDbid(@PathVariable("dbid") Integer dbid) {
        return gymOwnershipTermService.findByDbid(dbid);
    }

    // @AllowAll
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
