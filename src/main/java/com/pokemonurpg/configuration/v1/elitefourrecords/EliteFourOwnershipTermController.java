package com.pokemonurpg.configuration.v1.elitefourrecords;

import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.EliteFourOwnershipTerm;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;

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
@RequestMapping("/eliteFourOwnershipTerm")
@CrossOrigin
@Validated
public class EliteFourOwnershipTermController {

    @Resource
    private EliteFourOwnershipTermService eliteFourOwnershipTermService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<EliteFourOwnershipTerm> findAll() {
        return eliteFourOwnershipTermService.findAll();
    }

    @AllowAll
    @GetMapping(path="/{dbid}")
    public @ResponseBody
    EliteFourOwnershipTerm findByDbid(@PathVariable("dbid") Integer dbid) {
        return eliteFourOwnershipTermService.findByDbid(dbid);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    EliteFourOwnershipTerm create(@Valid @RequestBody EliteFourOwnershipTermInputDto input) {
        return eliteFourOwnershipTermService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    EliteFourOwnershipTerm update(@Valid @RequestBody EliteFourOwnershipTermInputDto input, @PathVariable int dbid) {
        return eliteFourOwnershipTermService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
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
