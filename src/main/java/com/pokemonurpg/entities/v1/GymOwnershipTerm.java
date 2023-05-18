package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermInputDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class GymOwnershipTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @OneToOne
    @JoinColumn(name = "gym_dbid")
    @JsonIgnoreProperties({"dbid", "badge", "type", "victories" })
    private Gym gym;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    @JsonIgnoreProperties({"dbid", "discordId", "salt", "accessToken", "refreshToken", "sessionExpire",
            "money", "wins", "losses", "draws", "joinDate", "pokemon", "items",
            "badges", "championRecords", "legendaryProgress", "earnedLegendaries", "roles",
            "banned", "banExpiration", "gyms", "bot", "eliteFourVictories", "championVictories", "gymVictories",
            "championTerms", "eliteFourTerms" })
    private Member owner;

    @OneToOne
    @JoinColumn(name = "league_dbid")
    @JsonIgnoreProperties("gyms")
    private GymLeague league;

    @Column(name = "open_date")
    private Date openDate;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Integer draws;

    @OneToOne
    @JoinColumn(name = "tm_dbid")
    private Item tm;

    public GymOwnershipTerm() {}

    public GymOwnershipTerm(GymOwnershipTermInputDto input) {
        this.update(input);
        setOpenDate(input.getOpenDate());
    }

    public void update(GymOwnershipTermInputDto input) {
        setDraws(input.getDraws());
        setLosses(input.getLosses());
        setWins(input.getWins());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        if (gym != null) {
            this.gym = gym;
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

    public Item getTm() {
        return tm;
    }

    public void setTm(Item tm) {
        if (tm != null) {
            this.tm = tm;
        }
    }
}
