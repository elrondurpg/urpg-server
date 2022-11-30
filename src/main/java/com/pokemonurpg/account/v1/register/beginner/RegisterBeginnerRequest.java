package com.pokemonurpg.account.v1.register.beginner;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.account.v1.register.beginner.internal.CreateStarterPokemonRequest;
import com.pokemonurpg.account.v1.register.common.RegisterMemberRequest;
import com.pokemonurpg.core.validation.RegisterNewUser;
import com.pokemonurpg.security.annotation.ValidStarter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterBeginnerRequest extends RegisterMemberRequest {
    
    @NotNull(groups = { RegisterNewUser.class })
    @ValidStarter(groups = { RegisterNewUser.class })
    private CreateStarterPokemonRequest starter;
}
