package com.pokemonurpg.configuration.v1.member.member.model;

import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.member.models.Role;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.*;
import com.pokemonurpg.stats.models.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends NamedConfigurationModel {
    private final static Random RANDOM = new Random();

    @Column(name = "discord_id")
    @JsonView(value = { MemberViews.Id.class })
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
    @JsonView(value = { MemberViews.Full.class })
    private Integer money;

    @Column
    @JsonView(value = { MemberViews.Full.class })
    private Integer wins;

    @Column
    @JsonView(value = { MemberViews.Full.class })
    private Integer losses;

    @Column
    @JsonView(value = { MemberViews.Full.class })
    private Integer draws;

    @Column(name = "join_date")
    @JsonView(value = { MemberViews.Full.class })
    private Date joinDate;

    @Column(name = "banned")
    @JsonView(value = { MemberViews.Full.class })
    private Boolean banned;

    @Column(name = "ban_expiration")
    @JsonView(value = { MemberViews.Full.class })
    private Date banExpiration;

    @OneToMany(mappedBy="trainer")
    @JsonView(value = { MemberViews.Full.class })
    private List<OwnedPokemon> pokemon;

    @OneToMany(mappedBy="trainer")
    @JsonView(value = { MemberViews.Full.class })
    private List<OwnedItem> items;

    @OneToMany(mappedBy="trainer")
    @JsonView(value = { MemberViews.Full.class })
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
    @JsonView(value = { MemberViews.Full.class })
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy="owner")
    @JsonView(value = { MemberViews.Full.class })
    private Set<GymOwnershipTerm> gyms;

    @OneToMany(mappedBy="owner")
    @JsonView(value = { MemberViews.Full.class })
    private Set<EliteFourOwnershipTerm> eliteFourTerms;

    @OneToMany(mappedBy="owner")
    @JsonView(value = { MemberViews.Full.class })
    private Set<ChampionOwnershipTerm> championTerms;

    @Column
    @JsonView(value = { MemberViews.Id.class })
    private Boolean bot;

    @OneToMany(mappedBy="challenger")
    @JsonView(value = { MemberViews.Full.class })
    private List<EliteFourVictory> eliteFourVictories;

    @OneToMany(mappedBy="challenger")
    @JsonView(value = { MemberViews.Full.class })
    private List<ChampionVictory> championVictories;

    @OneToMany(mappedBy="challenger")
    @JsonView(value = { MemberViews.Full.class })
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
