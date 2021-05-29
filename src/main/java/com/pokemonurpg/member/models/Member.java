package com.pokemonurpg.member.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.stats.models.*;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class Member implements NamedObject {
    private final static Random RANDOM = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column(name = "discord_id")
    private String discordId;

    @Column
    private String name;

    @Column
    @JsonIgnore
    private Integer salt;

    @Column(name = "access_token")
    @JsonIgnore
    private String accessToken;

    @Column(name = "refresh_token")
    @JsonIgnore
    private String refreshToken;

    @Column(name = "refresh_token_iv")
    @JsonIgnore
    private byte[] refreshTokenIv;

    @Column(name = "session_expire")
    @JsonIgnore
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

    @Column(name = "banned")
    private Boolean banned;

    @Column(name = "ban_expiration")
    private Date banExpiration;

    @OneToMany(mappedBy="trainer")
    @JsonIgnoreProperties("trainer")
    private List<OwnedPokemon> pokemon;

    @OneToMany(mappedBy="trainer")
    @JsonIgnoreProperties("trainer")
    private List<OwnedItem> items;

    @OneToMany(mappedBy="trainer")
    @JsonIgnoreProperties("trainer")
    private List<LegendaryProgress> legendaryProgress;

    @ManyToMany(
            targetEntity= Role.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="MEMBER_ROLE",
            joinColumns=@JoinColumn(name="MEMBER_DBID"),
            inverseJoinColumns=@JoinColumn(name="ROLE_DBID")
    )
    @JsonIgnoreProperties("members")
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy="owner")
    @JsonIgnoreProperties("owner")
    private Set<Gym> gyms;

    @Column
    private Boolean bot;

    @OneToMany(mappedBy="challenger")
    @JsonIgnoreProperties("challenger")
    private List<EliteFourVictory> eliteFourVictories;

    @OneToMany(mappedBy="challenger")
    @JsonIgnoreProperties("challenger")
    private List<ChampionVictory> championVictories;

    @OneToMany(mappedBy="challenger")
    @JsonIgnoreProperties("challenger")
    private List<GymVictory> gymVictories;

    public Member() {
    }

    public Member(MemberInputDto input) {
        this.update(input);
        salt = RANDOM.nextInt(1000000000);
    }

    public void update(MemberInputDto input) {
        setDiscordId(input.getDiscordId());
        setName(input.getName());
        setMoney(input.getMoney());
        setWins(input.getWins());
        setLosses(input.getLosses());
        setDraws(input.getDraws());
        setJoinDate(input.getJoinDate());
        setBot(input.getBot());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        if (discordId != null) {
            this.discordId = discordId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
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

    public byte[] getRefreshTokenIv() {
        return refreshTokenIv;
    }

    public void setRefreshTokenIv(byte[] refreshTokenIv) {
        this.refreshTokenIv = refreshTokenIv;
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
        if (money != null) {
            this.money = money;
        }
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        if (wins != null) {
            this.wins = wins;
        }
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        if (losses != null) {
            this.losses = losses;
        }
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        if (draws != null) {
            this.draws = draws;
        }
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
        if (joinDate != null) {
            this.joinDate = joinDate;
        }
    }

    public List<LegendaryProgress> getLegendaryProgress() {
        return legendaryProgress;
    }

    public void setLegendaryProgress(List<LegendaryProgress> legendaryProgress) {
        this.legendaryProgress = legendaryProgress;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Date getBanExpiration() {
        return banExpiration;
    }

    public void setBanExpiration(Date banExpiration) {
        this.banExpiration = banExpiration;
    }

    public Set<Gym> getGyms() {
        return gyms;
    }

    public void setGyms(Set<Gym> gyms) {
        this.gyms = gyms;
    }

    public Boolean getBot() {
        return bot;
    }

    public void setBot(Boolean bot) {
        if (bot != null) {
            this.bot = bot;
        }
    }

    public List<EliteFourVictory> getEliteFourVictories() {
        return eliteFourVictories;
    }

    public void setEliteFourVictories(List<EliteFourVictory> eliteFourVictories) {
        this.eliteFourVictories = eliteFourVictories;
    }

    public List<ChampionVictory> getChampionVictories() {
        return championVictories;
    }

    public void setChampionVictories(List<ChampionVictory> championVictories) {
        this.championVictories = championVictories;
    }

    public List<GymVictory> getGymVictories() {
        return gymVictories;
    }

    public void setGymVictories(List<GymVictory> gymVictories) {
        this.gymVictories = gymVictories;
    }
}
