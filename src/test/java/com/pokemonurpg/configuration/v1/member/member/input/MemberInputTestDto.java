package com.pokemonurpg.configuration.v1.member.member.input;

import java.util.Collections;
import java.util.Date;

import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class MemberInputTestDto extends MemberInputDto {
    public MemberInputTestDto() {
        this.setDiscordId(RandomStringGenerator.generate());
        this.setName(RandomStringGenerator.generate());
        this.setMoney(RandomNumberGenerator.generate());
        this.setWins(RandomNumberGenerator.generate());
        this.setLosses(RandomNumberGenerator.generate());
        this.setDraws(RandomNumberGenerator.generate());
        this.setJoinDate(new Date());
        this.setBot(false);
        this.setRoles(Collections.singletonList(new MemberRoleInputDto()));
        this.setLegendaryProgress(Collections.singletonList(new LegendaryProgressInputDto()));
        this.setItems(Collections.singletonList(new OwnedItemInputDto()));
        this.setEliteFourVictories(Collections.singletonList(new EliteFourVictoryInputDto()));
        this.setChampionVictories(Collections.singletonList(new ChampionVictoryInputDto()));
        this.setGymVictories(Collections.singletonList(new GymVictoryInputDto()));
    }
}
