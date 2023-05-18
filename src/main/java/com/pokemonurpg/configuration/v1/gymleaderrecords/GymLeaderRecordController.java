package com.pokemonurpg.configuration.v1.gymleaderrecords;

import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.GymLeaderRecord;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
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
@RequestMapping("/urpg-configuration/v1/gym-leader-records")
@CrossOrigin
@Validated
public class GymLeaderRecordController {

    @Resource
    private GymLeaderRecordService gymLeaderRecordService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<GymLeaderRecord> findAll() {
        return gymLeaderRecordService.findAll();
    }

    @AllowAll
    @GetMapping(path="/{dbid}")
    public @ResponseBody
    GymLeaderRecord findByDbid(@PathVariable("dbid") Integer dbid) {
        return gymLeaderRecordService.findByDbid(dbid);
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
    GymLeaderRecord create(@Valid @RequestBody GymLeaderRecordRequest input) {
        return gymLeaderRecordService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    GymLeaderRecord update(@Valid @RequestBody GymLeaderRecordRequest input, @PathVariable int dbid) {
        return gymLeaderRecordService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Gym")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            gymLeaderRecordService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
