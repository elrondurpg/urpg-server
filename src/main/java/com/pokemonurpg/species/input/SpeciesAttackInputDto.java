package com.pokemonurpg.species.input;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SpeciesAttackInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Attack.class)
    private String name;

    @Pattern(regexp = "^(LEVEL-UP|TM|HM|BREEDING|MOVE TUTOR|SPECIAL)$")
    private String method;

    @Min(1)
    @Max(99)
    private Integer generation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }
}
