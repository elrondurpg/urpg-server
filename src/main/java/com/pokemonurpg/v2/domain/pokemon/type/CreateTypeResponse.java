package com.pokemonurpg.v2.domain.pokemon.type;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTypeResponse {
    private Integer dbid;
    private String name;
}
