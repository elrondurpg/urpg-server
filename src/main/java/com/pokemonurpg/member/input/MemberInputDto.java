package com.pokemonurpg.member.input;


import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.stats.input.EarnedBadgeInputDto;
import com.pokemonurpg.stats.input.LegendaryProgressInputDto;
import com.pokemonurpg.stats.input.OwnedItemInputDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "\\d+")
    private String discordId;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String username;

    private Integer money;

    @Min(0)
    private Integer wins;

    @Min(0)
    private Integer losses;

    @Min(0)
    private Integer draws;

    @NotNull(groups = { ObjectCreation.class })
    private Date joinDate;

    @NotNull(groups = { ObjectCreation.class })
    private List<@Valid MemberRoleInputDto> roles = new ArrayList<>();

    private List<@Valid EarnedBadgeInputDto> badges = new ArrayList<>();

    private List<@Valid LegendaryProgressInputDto> legendaryProgress = new ArrayList<>();

    private List<@Valid OwnedItemInputDto> items = new ArrayList<>();

    private Boolean bot = false;

    private String botAccessToken = null;

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<EarnedBadgeInputDto> getBadges() {
        return badges;
    }

    public void setBadges(List<EarnedBadgeInputDto> badges) {
        this.badges = badges;
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

    public String getBotAccessToken() {
        return botAccessToken;
    }

    public void setBotAccessToken(String botAccessToken) {
        this.botAccessToken = botAccessToken;
    }
}
