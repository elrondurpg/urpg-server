package com.pokemonurpg.configuration.v1.create.member.beginner;

import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberRequest;
import com.pokemonurpg.configuration.v1.create.pokemon.starter.CreateStarterPokemonRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CreateBeginnerRequest extends CreateMemberRequest {
    private CreateStarterPokemonRequest starter;
}
