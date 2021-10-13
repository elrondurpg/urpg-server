package com.pokemonurpg.gym.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.gym.input.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.gym.input.GymOwnershipTermInputDto;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class EliteFourOwnershipTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @OneToOne
    @JoinColumn(name = "slot_dbid")
    @JsonIgnoreProperties({"dbid", "pokemon" })
    private EliteFour slot;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    @JsonIgnoreProperties({"dbid", "discordId", "salt", "accessToken", "refreshToken", "sessionExpire",
            "money", "wins", "losses", "draws", "joinDate", "pokemon", "items",
            "badges", "championRecords", "legendaryProgress", "earnedLegendaries", "roles",
            "banned", "banExpiration", "gyms", "bot", "eliteFourVictories", "championVictories", "gymVictories" })
    private Member owner;

    @Column(name = "open_date")
    private Date openDate;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Integer draws;

    public EliteFourOwnershipTerm() {}

    public EliteFourOwnershipTerm(EliteFourOwnershipTermInputDto input) {
        this.update(input);
        setOpenDate(input.getOpenDate());
    }

    public void update(EliteFourOwnershipTermInputDto input) {
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

    public EliteFour getSlot() {
        return slot;
    }

    public void setSlot(EliteFour slot) {
        this.slot = slot;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        if (owner != null) {
            this.owner = owner;
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
}
