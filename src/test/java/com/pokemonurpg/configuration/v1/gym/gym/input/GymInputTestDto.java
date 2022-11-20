package com.pokemonurpg.configuration.v1.gym.gym.input;

import com.pokemonurpg.configuration.v1.gym.badge.model.Badge;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class GymInputTestDto extends GymInputDto {
    public final static Type TYPE = new Type();
    public final static Badge BADGE = new Badge();
    public final static GymOwnershipTerm OWNER_RECORD = new GymOwnershipTerm();

    public GymInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setType(RandomStringGenerator.generate());
        this.setBadge(RandomStringGenerator.generate());
        this.setCurrentOwnerRecordDbid(RandomNumberGenerator.generate());
        this.setRemoveOwner(false);
    }
}
