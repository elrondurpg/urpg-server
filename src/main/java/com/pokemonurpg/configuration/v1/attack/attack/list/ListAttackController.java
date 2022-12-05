package com.pokemonurpg.configuration.v1.attack.attack.list;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v1.attack.attack.shared.view.ListAttackResponse;
import com.pokemonurpg.configuration.v1.shared.controller.ListController;
import com.pokemonurpg.configuration.v1.shared.handler.ListInputBoundary;

@RestController
@RequestMapping("configuration/v1/attack/attack")
@CrossOrigin
@Validated
public class ListAttackController extends ListController<ListAttackRequest, ListAttackResponse> {
    public ListAttackController(ListInputBoundary<ListAttackRequest, ListAttackResponse> handler) {
        super(handler);
    }
}
