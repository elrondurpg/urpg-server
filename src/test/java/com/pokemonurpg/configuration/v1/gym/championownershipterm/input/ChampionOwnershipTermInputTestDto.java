package com.pokemonurpg.configuration.v1.gym.championownershipterm.input;

import java.util.Date;

import com.pokemonurpg.configuration.v1.gym.champion.model.Champion;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class ChampionOwnershipTermInputTestDto extends ChampionOwnershipTermInputDto {
    public final static Champion SLOT = new Champion();
    public final static Member OWNER = new Member();
    
    public ChampionOwnershipTermInputTestDto() {
        this.setOpenDate(new Date());
        this.setWins(RandomNumberGenerator.generate());
        this.setLosses(RandomNumberGenerator.generate());
        this.setDraws(RandomNumberGenerator.generate());
        this.setOwner(RandomStringGenerator.generate());
        this.setSlot(RandomStringGenerator.generate());
        this.setBecomeCurrentOwner(true);
    }
}
