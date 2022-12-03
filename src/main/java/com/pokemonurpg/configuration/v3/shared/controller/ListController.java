package com.pokemonurpg.configuration.v3.shared.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pokemonurpg.configuration.v3.shared.interactor.ListInputBoundary;
import com.pokemonurpg.configuration.v3.shared.request.ListRequest;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

public abstract class ListController<View, Request extends ListRequest> {
    private ListInputBoundary<View, Request> inputBoundary;

    public ListController(ListInputBoundary<View, Request> inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
    
    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody PagedResponse<View> getList(@Valid Request request) {
        PagedResponse<View> response = inputBoundary.getList(request);
        return response;
    }
}
