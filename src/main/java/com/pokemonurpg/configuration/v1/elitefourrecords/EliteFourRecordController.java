package com.pokemonurpg.configuration.v1.elitefourrecords;

import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
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
@RequestMapping("/urpg-configuration/v1/elite-four-records")
@CrossOrigin
@Validated
public class EliteFourRecordController {

    @Resource
    private EliteFourRecordService eliteFourRecordService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<EliteFourMemberRecord> findAll() {
        return eliteFourRecordService.findAll();
    }

    @AllowAll
    @GetMapping(path="/{dbid}")
    public @ResponseBody
    EliteFourMemberRecord findByDbid(@PathVariable("dbid") Integer dbid) {
        return eliteFourRecordService.findByDbid(dbid);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    EliteFourMemberRecord create(@Valid @RequestBody EliteFourRecordRequest input) {
        return eliteFourRecordService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    EliteFourMemberRecord update(@Valid @RequestBody EliteFourRecordRequest input, @PathVariable int dbid) {
        return eliteFourRecordService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            eliteFourRecordService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
