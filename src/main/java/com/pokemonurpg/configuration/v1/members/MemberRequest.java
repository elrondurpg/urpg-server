package com.pokemonurpg.configuration.v1.members;


import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedRequest;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.DoesNotConflictWithKnownGymLeader;
import com.pokemonurpg.lib.v1.annotations.UniqueDiscordId;
import com.pokemonurpg.lib.v1.annotations.UniqueName;
import com.pokemonurpg.stats.v1.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@UniqueName(type = Member.class)
@UniqueDiscordId
public class MemberRequest implements UniquelyNamedRequest {

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "\\d+")
    private String discordId;

    @NotNull(groups = { ObjectCreation.class })
    @DoesNotConflictWithKnownGymLeader
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

    private List<@Valid MemberRoleRequest> roles = new ArrayList<>();

    private List<@Valid LegendaryProgressRequest> legendaryProgress = new ArrayList<>();

    private List<@Valid OwnedItemRequest> items = new ArrayList<>();

    private Boolean bot;

    private List<@Valid EliteFourVictoryRequest> eliteFourVictories = new ArrayList<>();

    private List<@Valid ChampionVictoryRequest> championVictories = new ArrayList<>();

    private List<@Valid GymVictoryRequest> gymVictories = new ArrayList<>();

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

    public List<MemberRoleRequest> getRoles() {
        return roles;
    }

    public void setRoles(List<MemberRoleRequest> roles) {
        this.roles = roles;
    }

    public List<LegendaryProgressRequest> getLegendaryProgress() {
        return legendaryProgress;
    }

    public void setLegendaryProgress(List<LegendaryProgressRequest> legendaryProgress) {
        this.legendaryProgress = legendaryProgress;
    }

    public List<OwnedItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OwnedItemRequest> items) {
        this.items = items;
    }

    public Boolean getBot() {
        return bot;
    }

    public void setBot(Boolean bot) {
        this.bot = bot;
    }

    public List<EliteFourVictoryRequest> getEliteFourVictories() {
        return eliteFourVictories;
    }

    public void setEliteFourVictories(List<EliteFourVictoryRequest> eliteFourVictories) {
        this.eliteFourVictories = eliteFourVictories;
    }

    public List<ChampionVictoryRequest> getChampionVictories() {
        return championVictories;
    }

    public void setChampionVictories(List<ChampionVictoryRequest> championVictories) {
        this.championVictories = championVictories;
    }

    public List<GymVictoryRequest> getGymVictories() {
        return gymVictories;
    }

    public void setGymVictories(List<GymVictoryRequest> gymVictories) {
        this.gymVictories = gymVictories;
    }
}
