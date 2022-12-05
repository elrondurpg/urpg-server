package com.pokemonurpg.configuration.v1.gym.knownelitefourmember.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.KnownEliteFourMemberViews;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.input.KnownEliteFourMemberInputDto;
import com.pokemonurpg.entities.v1.gym.KnownEliteFourMember;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.service.KnownEliteFourMemberService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/knownelitefourmember")
@CrossOrigin
@Validated
public class KnownEliteFourMemberController extends NamedConfigurationController<KnownEliteFourMember, FilterlessGetParams<KnownEliteFourMember>, KnownEliteFourMemberInputDto> {

    @Autowired
    public KnownEliteFourMemberController(KnownEliteFourMemberService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(KnownEliteFourMemberViews.Id.class)
            .withBriefViewClass(KnownEliteFourMemberViews.Id.class)
            .withFullViewClass(KnownEliteFourMemberViews.Id.class)
            .build(), service);
    }
}
