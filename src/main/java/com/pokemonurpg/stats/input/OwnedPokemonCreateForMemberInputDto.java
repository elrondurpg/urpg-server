package com.pokemonurpg.stats.input;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.member.models.Member;

import javax.validation.constraints.NotNull;

public class OwnedPokemonCreateForMemberInputDto extends OwnedPokemonInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Member.class)
    private String trainer;

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}
