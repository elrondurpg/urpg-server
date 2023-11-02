package com.pokemonurpg.gym.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.KnownEliteFourMemberInputDto;
import com.pokemonurpg.gym.models.KnownEliteFourMember;
import com.pokemonurpg.gym.service.KnownEliteFourMemberService;
import com.pokemonurpg.security.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-server/knownEliteFourMember")
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