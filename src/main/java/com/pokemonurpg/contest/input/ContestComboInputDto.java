package com.pokemonurpg.contest.input;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ContestComboInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Attack.class)
    private String secondAttack;

    @NotNull
    @Pattern(regexp = "^(RSE|DPP|ORAS|ADV)$")
    private String contestType;

    private Boolean overpowered = false;

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

    public String getContestType() {
        return contestType;
    }

    public void setContestType(String contestType) {
        this.contestType = contestType;
    }
}
