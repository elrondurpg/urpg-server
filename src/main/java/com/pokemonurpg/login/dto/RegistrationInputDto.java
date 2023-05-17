package com.pokemonurpg.login.dto;

import com.pokemonurpg.lib.input.UniquelyNamedInputDto;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.lib.validation.RegisterNewUser;
import com.pokemonurpg.lib.validation.annotation.DoesNotConflictWithKnownGymLeader;
import com.pokemonurpg.lib.validation.annotation.UniqueName;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.login.annotation.ValidStarter;
import com.pokemonurpg.stats.input.StarterPokemonInputDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = Member.class)
public class RegistrationInputDto implements UniquelyNamedInputDto {
    @NotEmpty
    private String code;

    @NotEmpty
    @DoesNotConflictWithKnownGymLeader(groups = {ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    @NotNull(groups = { RegisterNewUser.class })
    @ValidStarter(groups = { RegisterNewUser.class })
    private StarterPokemonInputDto starter;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StarterPokemonInputDto getStarter() {
        return starter;
    }

    public void setStarter(StarterPokemonInputDto starter) {
        this.starter = starter;
    }
}
