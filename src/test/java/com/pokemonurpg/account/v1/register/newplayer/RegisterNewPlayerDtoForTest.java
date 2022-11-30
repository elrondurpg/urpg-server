package com.pokemonurpg.account.v1.register.newplayer;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.core.validation.RegisterNewUser;

public class RegisterNewPlayerDtoForTest extends RegisterNewPlayerInputDto {

    public RegisterNewPlayerDtoForTest(@NotNull(groups = RegisterNewUser.class) CreateStarterPokemonInputDto starter) {
        super(starter);
        //TODO Auto-generated constructor stub
    }
    
}
