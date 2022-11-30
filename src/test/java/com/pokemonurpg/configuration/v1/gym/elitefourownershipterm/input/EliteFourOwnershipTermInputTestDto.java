package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.input;

import java.util.Date;

import com.pokemonurpg.configuration.v1.gym.elitefour.model.EliteFour;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class EliteFourOwnershipTermInputTestDto extends EliteFourOwnershipTermInputDto {
    public final static EliteFour SLOT = new EliteFour();
    public final static Member OWNER = new Member();

    public EliteFourOwnershipTermInputTestDto() {
        this.setOpenDate(new Date());
        this.setWins(RandomNumberGenerator.generate());
        this.setLosses(RandomNumberGenerator.generate());
        this.setDraws(RandomNumberGenerator.generate());
        this.setOwner(RandomStringGenerator.generate());
        this.setSlot(RandomStringGenerator.generate());
        this.setBecomeCurrentOwner(true);
    }
}
