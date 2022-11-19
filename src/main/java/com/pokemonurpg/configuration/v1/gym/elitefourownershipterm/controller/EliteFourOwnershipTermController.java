package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.IndexedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.EliteFourOwnershipTermViews;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.input.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.service.EliteFourOwnershipTermService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/elitefourownershipterm")
@CrossOrigin
@Validated
public class EliteFourOwnershipTermController extends IndexedConfigurationController<EliteFourOwnershipTerm, FilterlessGetParams<EliteFourOwnershipTerm>, EliteFourOwnershipTermInputDto> {

    @Autowired
    public EliteFourOwnershipTermController(EliteFourOwnershipTermService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(EliteFourOwnershipTermViews.Id.class)
            .withBriefViewClass(EliteFourOwnershipTermViews.Brief.class)
            .withFullViewClass(EliteFourOwnershipTermViews.Brief.class)
            .build(), service);
    }
}
