package com.pokemonurpg.configuration.v2.attack.attack.list;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v2.attack.attack.shared.view.ListAttackResponseWrapper;
import com.pokemonurpg.configuration.v2.shared.view.WrappedListResponse;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("configuration/v2/attack/attack")
@CrossOrigin
@Validated
@Api(tags = "Attack")
@AllArgsConstructor
public class ListAttackController {
    private ListAttackHandler handler;

    @GetMapping
    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    public @ResponseBody WrappedListResponse<ListAttackResponseWrapper> getList(@Valid ListAttackRequest request) {
        return new WrappedListResponse<ListAttackResponseWrapper>(handler.getList(request), ListAttackResponseWrapper.class);
    }
}
