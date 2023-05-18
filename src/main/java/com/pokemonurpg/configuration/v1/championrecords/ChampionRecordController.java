package com.pokemonurpg.configuration.v1.championrecords;

import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.ChampionRecord;
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
@RequestMapping("/urpg-configuration/v1/champion-records")
@CrossOrigin
@Validated
public class ChampionRecordController {

    @Resource
    private ChampionRecordService championRecordService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<ChampionRecord> findAll() {
        return championRecordService.findAll();
    }

    @AllowAll
    @GetMapping(path="/{dbid}")
    public @ResponseBody
    ChampionRecord findByDbid(@PathVariable("dbid") Integer dbid) {
        return championRecordService.findByDbid(dbid);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    ChampionRecord create(@Valid @RequestBody ChampionRecordRequest input) {
        return championRecordService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ChampionRecord update(@Valid @RequestBody ChampionRecordRequest input, @PathVariable int dbid) {
        return championRecordService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            championRecordService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
