package com.pokemonurpg.v2.service.pokemon.type;

import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeModel implements Type {
    private Integer dbid;
    private String name;
}
