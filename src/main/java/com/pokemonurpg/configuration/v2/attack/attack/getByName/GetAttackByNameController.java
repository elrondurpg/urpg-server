package com.pokemonurpg.configuration.v2.attack.attack.getByName;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v2.attack.attack.shared.view.GetAttackResponse;
import com.pokemonurpg.configuration.v2.attack.attack.shared.view.GetAttackResponseWrapper;
import com.pokemonurpg.configuration.v2.shared.handler.GetByNameInputBoundary;
import com.pokemonurpg.configuration.v2.shared.view.EntityWrapper;
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
public class GetAttackByNameController {
    private GetByNameInputBoundary<GetAttackResponse> handler;

    @GetMapping(path="/{name}")
    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    public @ResponseBody GetAttackResponseWrapper getByName(@PathVariable("name") String name) {
        return EntityWrapper.wrapEntityInClass(handler.getByName(name), GetAttackResponseWrapper.class);
    }
}
