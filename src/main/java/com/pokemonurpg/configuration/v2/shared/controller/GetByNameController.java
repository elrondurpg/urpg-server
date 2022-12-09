package com.pokemonurpg.configuration.v2.shared.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v2.attack.attack.shared.view.GetAttackResponse;
import com.pokemonurpg.configuration.v2.shared.handler.GetByNameInputBoundary;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class GetByNameController<ResponseType> {
    private GetByNameInputBoundary<ResponseType> handler;

    @GetMapping(path="/{name}")
    @JsonSerialize(as = GetAttackResponse.class)
    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    public @ResponseBody ResponseEntity<ResponseType> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(handler.getByName(name));
    }
}
