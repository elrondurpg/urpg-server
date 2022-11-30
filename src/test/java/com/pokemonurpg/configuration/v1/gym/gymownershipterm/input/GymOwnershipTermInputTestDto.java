package com.pokemonurpg.configuration.v1.gym.gymownershipterm.input;

import java.util.Date;

import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class GymOwnershipTermInputTestDto extends GymOwnershipTermInputDto {
    public final static Gym GYM = new Gym();
    public final static Member OWNER = new Member();
    public final static Item ITEM = new Item();
    public final static League LEAGUE = new League();

    public GymOwnershipTermInputTestDto() {
        this.setOpenDate(new Date());
        this.setWins(RandomNumberGenerator.generate());
        this.setLosses(RandomNumberGenerator.generate());
        this.setDraws(RandomNumberGenerator.generate());
        this.setTm(RandomStringGenerator.generate());
        this.setOwner(RandomStringGenerator.generate());
        this.setLeague(RandomStringGenerator.generate());
        this.setGym(RandomStringGenerator.generate());
        this.setBecomeCurrentOwner(true);
    }
}
