package com.pokemonurpg.species.input;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TypeInputDto {
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
