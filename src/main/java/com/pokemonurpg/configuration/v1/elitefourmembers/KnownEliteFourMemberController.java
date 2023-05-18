package com.pokemonurpg.configuration.v1.elitefourmembers;

import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.KnownEliteFourMember;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/knownEliteFourMember")
@CrossOrigin
@Validated
public class KnownEliteFourMemberController {

    @Resource
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return knownEliteFourMemberService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    KnownEliteFourMember findByName(@PathVariable("name") String name) {
        return knownEliteFourMemberService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    KnownEliteFourMember create(@Valid @RequestBody KnownEliteFourMemberInputDto input) {
        return knownEliteFourMemberService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    KnownEliteFourMember update(@Valid @RequestBody KnownEliteFourMemberInputDto input, @PathVariable int dbid) {
        return knownEliteFourMemberService.update(input, dbid);
    }
}
