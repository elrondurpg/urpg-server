package com.pokemonurpg.core.security.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AuthenticatedInputDto {

    @Valid
    @NotNull
    private SessionDto session;

    public SessionDto getSession() {
        return session;
    }

    public void setSession(SessionDto session) {
        this.session = session;
    }

}
