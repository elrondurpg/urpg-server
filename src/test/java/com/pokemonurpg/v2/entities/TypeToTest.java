package com.pokemonurpg.v2.entities;

import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TypeToTest implements Type {
    public static final Integer DBID = 202505;
    public static final String NAME = "TypeToTest";

    private Integer dbid;
    private String name;
}
