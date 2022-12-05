package com.pokemonurpg.configuration.v1.attack.attack.input;

import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.entities.v1.contest.ContestType;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.validation.constraints.NotNull;

public class ContestComboInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Attack.class)
    private String secondAttack;

    @NotNull
    @ExistsInDb(type = ContestType.class)
    private String generation;

    private Boolean overpowered;

    public Boolean getOverpowered() {
        return overpowered;
    }

    public void setOverpowered(Boolean overpowered) {
        this.overpowered = overpowered;
    }

    public String getSecondAttack() {
        return secondAttack;
    }

    public void setSecondAttack(String secondAttack) {
        this.secondAttack = secondAttack;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    
}
