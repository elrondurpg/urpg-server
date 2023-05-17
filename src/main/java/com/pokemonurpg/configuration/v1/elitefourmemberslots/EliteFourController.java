package com.pokemonurpg.configuration.v1.elitefourmemberslots;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.EliteFour;
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
@RequestMapping("/eliteFour")
@CrossOrigin
@Validated
public class EliteFourController {

    @Resource
    private EliteFourService eliteFourService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAll() {
        return eliteFourService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    EliteFour findByName(@PathVariable("name") String name) {
        return eliteFourService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    EliteFour create(@Valid @RequestBody EliteFourInputDto input) {
        return eliteFourService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    EliteFour update(@Valid @RequestBody EliteFourInputDto input, @PathVariable int dbid) {
        return eliteFourService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            eliteFourService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
