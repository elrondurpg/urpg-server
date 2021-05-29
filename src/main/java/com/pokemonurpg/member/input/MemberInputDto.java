package com.pokemonurpg.member.input;


import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@UniqueName(type = Member.class)
public class MemberInputDto implements UniquelyNamedInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "\\d+")
    private String discordId;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    private Integer money;

    @Min(0)
    private Integer wins;

    @Min(0)
    private Integer losses;

    @Min(0)
    private Integer draws;

    @NotNull(groups = { ObjectCreation.class })
    private Date joinDate;

    private List<@Valid MemberRoleInputDto> roles = new ArrayList<>();

    private List<@Valid LegendaryProgressInputDto> legendaryProgress = new ArrayList<>();

    private List<@Valid OwnedItemInputDto> items = new ArrayList<>();

    private Boolean bot;

    private List<@Valid EliteFourVictoryInputDto> eliteFourVictories = new ArrayList<>();

    private List<@Valid ChampionVictoryInputDto> championVictories = new ArrayList<>();

    private List<@Valid GymVictoryInputDto> gymVictories = new ArrayList<>();

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public List<MemberRoleInputDto> getRoles() {
        return roles;
    }

    public void setRoles(List<MemberRoleInputDto> roles) {
        this.roles = roles;
    }

    public List<LegendaryProgressInputDto> getLegendaryProgress() {
        return legendaryProgress;
    }

    public void setLegendaryProgress(List<LegendaryProgressInputDto> legendaryProgress) {
        this.legendaryProgress = legendaryProgress;
    }

    public List<OwnedItemInputDto> getItems() {
        return items;
    }

    public void setItems(List<OwnedItemInputDto> items) {
        this.items = items;
    }

    public Boolean getBot() {
        return bot;
    }

    public void setBot(Boolean bot) {
        this.bot = bot;
    }

    public List<EliteFourVictoryInputDto> getEliteFourVictories() {
        return eliteFourVictories;
    }

    public void setEliteFourVictories(List<EliteFourVictoryInputDto> eliteFourVictories) {
        this.eliteFourVictories = eliteFourVictories;
    }

    public List<ChampionVictoryInputDto> getChampionVictories() {
        return championVictories;
    }

    public void setChampionVictories(List<ChampionVictoryInputDto> championVictories) {
        this.championVictories = championVictories;
    }

    public List<GymVictoryInputDto> getGymVictories() {
        return gymVictories;
    }

    public void setGymVictories(List<GymVictoryInputDto> gymVictories) {
        this.gymVictories = gymVictories;
    }
}
