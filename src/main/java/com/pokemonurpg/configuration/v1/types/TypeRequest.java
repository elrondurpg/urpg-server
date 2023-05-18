package com.pokemonurpg.configuration.v1.types;



import com.pokemonurpg.entities.v1.Type;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedRequest;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = Type.class)
public class TypeRequest implements UniquelyNamedRequest {
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
