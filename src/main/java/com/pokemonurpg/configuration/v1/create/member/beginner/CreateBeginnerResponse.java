package com.pokemonurpg.configuration.v1.create.member.beginner;

import java.util.List;

import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberResponse;
import com.pokemonurpg.configuration.v1.create.pokemon.starter.CreateStarterPokemonResponse;
import com.pokemonurpg.configuration.v1.member.member.model.OwnedItem;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CreateBeginnerResponse extends CreateMemberResponse {
    private CreateStarterPokemonResponse starter;
    private List<OwnedItem> items;
}
