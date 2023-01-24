package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TypeModel implements Type {
    private Integer dbid;
    private String name;
}
