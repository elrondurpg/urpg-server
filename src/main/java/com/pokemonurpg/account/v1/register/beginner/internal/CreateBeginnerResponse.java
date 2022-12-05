package com.pokemonurpg.account.v1.register.beginner.internal;

import java.util.List;

import com.pokemonurpg.account.v1.register.common.internal.CreateMemberResponse;
import com.pokemonurpg.entities.v1.member.OwnedItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Setter
@NoArgsConstructor
public class CreateBeginnerResponse extends CreateMemberResponse {
    private CreateStarterPokemonResponse starter;
    private List<OwnedItem> items;
}
