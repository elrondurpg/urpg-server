package com.pokemonurpg.configuration.v1.gym.elitefour.input;

import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;
import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class EliteFourInputTestDto extends EliteFourInputDto {
    public final static EliteFourOwnershipTerm OWNER_RECORD = new EliteFourOwnershipTerm();
    public EliteFourInputTestDto() {
        this.setName(RandomStringGenerator.generate());
        this.setCurrentOwnerRecordDbid(RandomNumberGenerator.generate());
        this.setRemoveOwner(false);
    }
}
