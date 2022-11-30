package com.pokemonurpg.account.v1.register.newplayer;

import javax.validation.constraints.NotNull;

import com.pokemonurpg.account.v1.register.RegisterPlayerDto;
import com.pokemonurpg.core.validation.RegisterNewUser;
import com.pokemonurpg.security.annotation.ValidStarter;
import com.pokemonurpg.stats.input.StarterPokemonInputDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterNewPlayerDto extends RegisterPlayerDto {
    
    @NotNull(groups = { RegisterNewUser.class })
    @ValidStarter(groups = { RegisterNewUser.class })
    private StarterPokemonInputDto starter;
}
