package com.pokemonurpg.account.v1.register.newplayer;

import com.pokemonurpg.configuration.v1.member.member.model.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateStarterPokemonDto {
    private RegisterNewPlayerDto input;
    private Member member;
}
