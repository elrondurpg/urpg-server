package com.pokemonurpg.gym.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.EarnedBadge;
import com.pokemonurpg.stats.models.OwnedPokemon;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    @JsonIgnoreProperties({"dbid", "discordId", "salt", "accessToken", "refreshToken", "sessionExpire",
            "money", "wins", "losses", "draws", "joinDate", "pokemon", "items",
            "badges", "championRecords", "legendaryProgress", "earnedLegendaries", "roles",
            "banned", "banExpiration", "gyms" })
    private Member owner;

    @OneToOne
    @JoinColumn(name = "league_dbid")
    @JsonIgnoreProperties("gyms")
    private GymLeague league;

    @OneToOne
    @JoinColumn(name = "badge_dbid")
    private Badge badge;

    @Column
    private Boolean active;

    @Column(name = "open_date")
    private Date openDate;

    @Column
    private Integer wins = 0;

    @Column
    private Integer losses = 0;

    @Column
    private Integer draws = 0;

    @OneToOne
    @JoinColumn(name = "tm_dbid")
    private Item tm;

//    @OneToMany(mappedBy="gym")
//    @JsonIgnoreProperties("gym")
//    private List<EarnedBadge> winners;

    @ManyToMany(
            targetEntity= OwnedPokemon.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="GYM_POKEMON",
            joinColumns=@JoinColumn(name="GYM_DBID"),
            inverseJoinColumns=@JoinColumn(name="POKEMON_DBID")
    )
    private Set<OwnedPokemon> pokemon = new HashSet<>();

    public Gym() {
    }

    public Gym (GymInputDto input) {
        this.update(input);
    }

    public void update(GymInputDto input) {
        setName(input.getName());
        setActive(input.getActive());
        setOpenDate(input.getOpenDate());
        setWins(input.getWins());
        setLosses(input.getLosses());
        setDraws(input.getDraws());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        if (owner != null) {
            this.owner = owner;
        }
    }

    public GymLeague getLeague() {
        return league;
    }

    public void setLeague(GymLeague league) {
        if (league != null) {
            this.league = league;
        }
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        if (badge != null) {
            this.badge = badge;
        }
    }

    public void setActive(Boolean active) {
        if (active != null) {
            this.active = active;
        }
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        if (openDate != null) {
            this.openDate = openDate;
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

    public Boolean getActive() {
        return active;
    }

    public Item getTm() {
        return tm;
    }

    public void setTm(Item tm) {
        if (tm != null) {
            this.tm = tm;
        }
    }

//    public List<EarnedBadge> getWinners() {
//        return winners;
//    }
//
//    public void setWinners(List<EarnedBadge> winners) {
//        this.winners = winners;
//    }

    public Set<OwnedPokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(Set<OwnedPokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
