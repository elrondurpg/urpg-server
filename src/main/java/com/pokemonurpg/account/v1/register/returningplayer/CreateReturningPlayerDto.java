package com.pokemonurpg.account.v1.register.returningplayer;

import com.pokemonurpg.account.v1.register.CreatePlayerDto;
import com.pokemonurpg.account.v1.register.RegisterPlayerDto;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreateReturningPlayerDto extends CreatePlayerDto<RegisterPlayerDto> {}
