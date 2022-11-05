package com.pokemonurpg.configuration.v1.pokemon.type.input;



import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = Type.class)
public class TypeInputDto implements UniquelyNamedInputDto {
    @NotNull
    @Size(min = 3, max = 10)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
