package com.pokemonurpg.member.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.core.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin
@Validated
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return memberService.findAllNames();
    }

    @GetMapping(path="/{name}")
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Member findByName(@PathVariable("name") String name) {
        return memberService.findByUsername(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Member")
    @PostMapping
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Member create(@Valid @RequestBody MemberInputDto input) {
        return memberService.create(input);
    }

    @Authorized(permission = "Write Member")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Member update(@Valid @RequestBody MemberInputDto input, @PathVariable int dbid) {
        return memberService.update(input, dbid);
    }
}
