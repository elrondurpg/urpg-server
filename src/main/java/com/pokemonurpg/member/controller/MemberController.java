package com.pokemonurpg.member.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.security.annotation.AllowThisMember;
import com.pokemonurpg.security.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_CLAIM;
import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_DELETE;
import static com.pokemonurpg.strings.PermissionNames.WRITE_MEMBER_PERMISSION;
import static com.pokemonurpg.strings.PermissionNames.WRITE_ROLE_PERMISSION;

@RestController
@RequestMapping("/urpg-server/member")
@CrossOrigin
@Validated
public class MemberController {

    @Resource
    private MemberService memberService;

    @Resource
    private AuthorizationService authorizationService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findNamesBy(@RequestParam(required = false) String username, @RequestParam(required = false) Boolean bot) {
        return memberService.findNamesBy(username, bot);
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    MappingJacksonValue findByName(@PathVariable("name") String name) {
        MappingJacksonValue value = new MappingJacksonValue( memberService.findByName(name) );
        if( authorizationService.isAuthorized(WRITE_ROLE_PERMISSION) ) {
            value.setSerializationView( View.MemberView.Secure.class );
        } else {
            value.setSerializationView( View.MemberView.Summary.class );
        }
        return value;
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = WRITE_MEMBER_PERMISSION)
    @PostMapping
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    Member create(@Valid @RequestBody MemberInputDto input) {
        return memberService.create(input);
    }

    @AllowAuthorized(permission = WRITE_MEMBER_PERMISSION)
    @PostMapping(path="/approveKnownNameClaim/{discordId}")
    public @ResponseBody
    ResponseEntity approveKnownNameClaim(@PathVariable String discordId) {
        try {
            memberService.registerForKnownName(discordId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_CLAIM);
        }
    }

    @AllowThisMember
    @AllowAuthorized(permission = WRITE_MEMBER_PERMISSION)
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    MappingJacksonValue update(@Valid @RequestBody MemberInputDto input, @PathVariable int dbid) {
        MappingJacksonValue value = new MappingJacksonValue( memberService.update(input, dbid) );
        if( authorizationService.isAuthorized(WRITE_MEMBER_PERMISSION) ) {
            value.setSerializationView( View.MemberView.Secure.class );
        } else {
            value.setSerializationView( View.MemberView.Summary.class );
        }
        return value;
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Member")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            memberService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
