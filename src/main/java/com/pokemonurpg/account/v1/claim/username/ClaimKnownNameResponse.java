package com.pokemonurpg.account.v1.claim.username;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClaimKnownNameResponse {
    private String discordId;
    private String name;
}
