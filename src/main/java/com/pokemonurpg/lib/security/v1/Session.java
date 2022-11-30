package com.pokemonurpg.lib.security.v1;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Session {
    @NotEmpty
    private String username;

    @NotEmpty
    private String id;

    @NotEmpty
    private String accessToken;
}
