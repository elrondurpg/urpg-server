package com.pokemonurpg.configuration.v1.member.member.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.member.member.input.MemberGetParams;
import com.pokemonurpg.configuration.v1.member.member.input.MemberInputDto;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/member/member")
@CrossOrigin
@Validated
public class MemberController extends NamedConfigurationController<Member, MemberGetParams, MemberInputDto> {

    @Autowired
    public MemberController(MemberService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(MemberViews.Id.class)
            .withBriefViewClass(MemberViews.Brief.class)
            .withFullViewClass(MemberViews.Full.class)
            .build(), service);
    }
}
