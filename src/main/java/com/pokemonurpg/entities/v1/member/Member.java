package com.pokemonurpg.entities.v1.member;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.entities.v1.gym.ChampionOwnershipTerm;
import com.pokemonurpg.entities.v1.gym.EliteFourOwnershipTerm;
import com.pokemonurpg.entities.v1.gym.GymOwnershipTerm;
import com.pokemonurpg.entities.v1.shared.NamedEntity;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends NamedEntity {
    private final static Random RANDOM = new Random();

    @Column(name = "discord_id")
    private String discordId;

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
    private List<OwnedPokemon> pokemon;

    @OneToMany(mappedBy="trainer")
    private List<OwnedItem> items;

    @OneToMany(mappedBy="trainer")
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
    private Set<GymOwnershipTerm> gyms;

    @OneToMany(mappedBy="owner")
    private Set<EliteFourOwnershipTerm> eliteFourTerms;

    @OneToMany(mappedBy="owner")
    private Set<ChampionOwnershipTerm> championTerms;

    @Column
    private Boolean bot;

    @OneToMany(mappedBy="challenger")
    private List<EliteFourVictory> eliteFourVictories;

    @OneToMany(mappedBy="challenger")
    private List<ChampionVictory> championVictories;

    @OneToMany(mappedBy="challenger")
    private List<GymVictory> gymVictories;

    @Override
    public void setDefaultValues() {
        setSalt(RANDOM.nextInt(1000000000));
        if (bot == null) setBot(false);
    }

    public boolean hasStarter() {
        return pokemon.stream().anyMatch(ownedPokemon -> "Starter".equalsIgnoreCase(ownedPokemon.getObtained().getName()));
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
