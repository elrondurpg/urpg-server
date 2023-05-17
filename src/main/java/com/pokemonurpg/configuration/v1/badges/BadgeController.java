package com.pokemonurpg.configuration.v1.badges;

import com.pokemonurpg.login.annotation.AllowAll;
import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.Badge;
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
@RequestMapping("/badge")
@CrossOrigin
@Validated
public class BadgeController {

    @Resource
    private BadgeService badgeService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return badgeService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    Badge findByName(@PathVariable("name") String name) {
        return badgeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    Badge create(@Valid @RequestBody BadgeInputDto input) {
        return badgeService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Badge update(@Valid @RequestBody BadgeInputDto input, @PathVariable int dbid) {
        return badgeService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            badgeService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}