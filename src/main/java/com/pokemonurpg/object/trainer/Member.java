package com.pokemonurpg.object.trainer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int dbid;

    @Column(name = "discord_id")
    private String discordId;

    @Column
    private String username;

    @Column
    private Integer salt;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "session_expire")
    private Long sessionExpire;

    @Column
    private Integer money;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Integer draws;

    @Column(name = "join_date")
    private Date joinDate;

    @OneToMany(mappedBy="trainer")
    private List<OwnedPokemon> pokemon;

    @OneToMany(mappedBy="trainer")
    private List<OwnedItem> items;

    @OneToMany(mappedBy="member")
    private List<MemberRole> roles;

    @OneToMany(mappedBy="member")
    private List<EarnedBadge> badges;

    @OneToMany(mappedBy="member")
    private List<ChampionRecord> championRecords;

    @OneToMany(mappedBy="trainer")
    private List<LegendaryProgress> legendaryProgress;

    @OneToMany(mappedBy="trainer")
    private List<EarnedLegendary> earnedLegendaries;

    public Member() {
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

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

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getSessionExpire() {
        return sessionExpire;
    }

    public void setSessionExpire(Long sessionExpire) {
        this.sessionExpire = sessionExpire;
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

    public List<OwnedPokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<OwnedPokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public List<OwnedItem> getItems() {
        return items;
    }

    public void setItems(List<OwnedItem> items) {
        this.items = items;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public List<MemberRole> getRoles() {
        return roles;
    }

    public void setRoles(List<MemberRole> roles) {
        this.roles = roles;
    }

    public List<EarnedBadge> getBadges() {
        return badges;
    }

    public void setBadges(List<EarnedBadge> badges) {
        this.badges = badges;
    }

    public List<ChampionRecord> getChampionRecords() {
        return championRecords;
    }

    public void setChampionRecords(List<ChampionRecord> championRecords) {
        this.championRecords = championRecords;
    }

    public List<LegendaryProgress> getLegendaryProgress() {
        return legendaryProgress;
    }

    public void setLegendaryProgress(List<LegendaryProgress> legendaryProgress) {
        this.legendaryProgress = legendaryProgress;
    }

    public List<EarnedLegendary> getEarnedLegendaries() {
        return earnedLegendaries;
    }

    public void setEarnedLegendaries(List<EarnedLegendary> earnedLegendaries) {
        this.earnedLegendaries = earnedLegendaries;
    }
}
