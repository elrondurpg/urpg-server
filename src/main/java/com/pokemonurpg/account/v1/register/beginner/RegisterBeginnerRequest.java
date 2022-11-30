package com.pokemonurpg.account.v1.register.beginner;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.account.v1.register.common.RegisterPlayerRequest;
import com.pokemonurpg.configuration.v1.create.pokemon.starter.CreateStarterPokemonRequest;
import com.pokemonurpg.core.validation.RegisterNewUser;
import com.pokemonurpg.security.annotation.ValidStarter;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterBeginnerRequest extends RegisterPlayerRequest {
    
    @NotNull(groups = { RegisterNewUser.class })
    @ValidStarter(groups = { RegisterNewUser.class })
    private CreateStarterPokemonRequest starter;
}
