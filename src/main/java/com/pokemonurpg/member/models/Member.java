package com.pokemonurpg.member.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.stats.models.*;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class Member {
    private final static Random RANDOM = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column(name = "discord_id")
    private String discordId;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private Integer salt;

    @Column(name = "access_token")
    @JsonIgnore
    private String accessToken;

    @Column(name = "refresh_token")
    @JsonIgnore
    private String refreshToken;

    @Column(name = "session_expire")
    @JsonIgnore
    private Long sessionExpire;

    @Column
    private Integer money = 0;

    @Column
    private Integer wins = 0;

    @Column
    private Integer losses = 0;

    @Column
    private Integer draws = 0;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "banned")
    private Boolean banned = false;

    @Column(name = "ban_expiration")
    private Date banExpiration;

    @OneToMany(mappedBy="trainer")
    @JsonIgnoreProperties("trainer")
    private List<OwnedPokemon> pokemon;

    @OneToMany(mappedBy="trainer")
    @JsonIgnoreProperties("trainer")
    private List<OwnedItem> items;

    @OneToMany(mappedBy="member")
    @JsonIgnoreProperties("member")
    private List<EarnedBadge> badges;

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

    public Member() {
    }

    public Member(MemberInputDto input) {
        this.update(input);
        salt = RANDOM.nextInt(1000000000);
    }

    public void update(MemberInputDto input) {
        setDiscordId(input.getDiscordId());
        setUsername(input.getUsername());
        setMoney(input.getMoney());
        setWins(input.getWins());
        setLosses(input.getLosses());
        setDraws(input.getDraws());
        setJoinDate(input.getJoinDate());
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null) {
            this.username = username;
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

    public List<EarnedBadge> getBadges() {
        return badges;
    }

    public void setBadges(List<EarnedBadge> badges) {
        this.badges = badges;
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
}
