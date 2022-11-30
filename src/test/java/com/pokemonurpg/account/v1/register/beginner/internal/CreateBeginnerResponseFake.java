package com.pokemonurpg.account.v1.register.beginner.internal;

import java.util.Collections;

import com.pokemonurpg.configuration.v1.member.member.model.OwnedItem;
import com.pokemonurpg.item.models.Item;

public class CreateBeginnerResponseFake extends CreateBeginnerResponse {
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String ROLE = "ROLE";
    public static final String NAME = "NAME";
    public static final String DISCORD_ID = "DISCORD_ID";
    public static final String ITEM = "ITEM";
    public static final Integer QUANTITY = 234;

    public CreateBeginnerResponseFake() {
        this.setAccessToken(ACCESS_TOKEN);
        this.setDiscordId(DISCORD_ID);
        this.setName(NAME);
        this.setRoles(Collections.singletonList(ROLE));
        this.setStarter(new CreateStarterPokemonResponseFake());

        OwnedItem itemRecord = new OwnedItem();
        Item item = new Item();
        item.setName(ITEM);
        itemRecord.setItem(item);
        itemRecord.setQuantity(QUANTITY);
        this.setItems(Collections.singletonList(itemRecord));
    }
}
