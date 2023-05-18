package com.pokemonurpg.configuration.v1.elitefourmembers;

import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.EliteFourMember;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/elite-four-members")
@CrossOrigin
@Validated
public class EliteFourMemberController {

    @Resource
    private EliteFourMemberService eliteFourMemberService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return eliteFourMemberService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    EliteFourMember findByName(@PathVariable("name") String name) {
        return eliteFourMemberService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    EliteFourMember create(@Valid @RequestBody EliteFourMemberRequest input) {
        return eliteFourMemberService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    EliteFourMember update(@Valid @RequestBody EliteFourMemberRequest input, @PathVariable int dbid) {
        return eliteFourMemberService.update(input, dbid);
    }
}
