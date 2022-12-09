package com.pokemonurpg.configuration.v2.shared.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pokemonurpg.configuration.v2.shared.handler.ListInputBoundary;
import com.pokemonurpg.configuration.v2.shared.request.ListRequest;
import com.pokemonurpg.configuration.v2.shared.view.ListView;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListController<Request extends ListRequest, Response> {
    private ListInputBoundary<Request, Response> handler;

    @GetMapping
    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    public @ResponseBody ListView<Response> getList(@Valid Request request) throws JsonProcessingException, NoSuchFieldException {
        return handler.getList(request);
    }
}
