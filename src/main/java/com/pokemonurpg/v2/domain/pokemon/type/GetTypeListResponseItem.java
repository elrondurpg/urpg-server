package com.pokemonurpg.v2.domain.pokemon.type;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class GetTypeListResponseItem {
    private int dbid;
    private String name;
}
