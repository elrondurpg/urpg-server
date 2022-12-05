package com.pokemonurpg.configuration.v1.shared.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokemonurpg.configuration.v1.shared.handler.ListInputBoundary;
import com.pokemonurpg.configuration.v1.shared.request.ListRequest;
import com.pokemonurpg.configuration.v1.shared.view.ListView;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListController<Request extends ListRequest, Response> {
    private ListInputBoundary<Request, Response> handler;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    ListView<Response> getList(@Valid Request request) {
        return handler.getList(request);
    }
}
