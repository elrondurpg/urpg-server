package com.pokemonurpg.account.v1.register.veteran.internal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClaimKnownNameRequest {
    private String discordId;
    private String name;
}
