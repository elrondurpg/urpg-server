package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.input.KnownEliteFourMemberBulkInputDto;
import com.pokemonurpg.gym.service.KnownEliteFourMemberBulkService;
import com.pokemonurpg.gym.service.KnownEliteFourMemberService;
import com.pokemonurpg.security.annotation.Authorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/knownEliteFourMember")
@CrossOrigin
@Validated
public class KnownEliteFourMemberBulkController {

    @Resource
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Resource
    private KnownEliteFourMemberBulkService knownEliteFourMemberBulkService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return knownEliteFourMemberService.findAllNames();
    }

    @Authorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    List<String> update(@Valid @RequestBody KnownEliteFourMemberBulkInputDto input) {
        return knownEliteFourMemberBulkService.update(input);
    }
}