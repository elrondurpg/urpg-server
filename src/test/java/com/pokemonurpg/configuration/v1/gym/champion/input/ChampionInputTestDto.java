package com.pokemonurpg.configuration.v1.gym.champion.input;

import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class ChampionInputTestDto extends ChampionInputDto {
    public final static ChampionOwnershipTerm OWNER_RECORD = new ChampionOwnershipTerm();
    public ChampionInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setCurrentOwnerRecordDbid(RandomNumberGenerator.generate());
        this.setRemoveOwner(false);
    }
}
