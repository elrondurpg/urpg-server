package com.pokemonurpg.gym.input;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.core.validation.annotation.UniqueId;
import com.pokemonurpg.gym.models.Champion;
import com.pokemonurpg.member.models.Member;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@UniqueId(groups = { ObjectCreation.class }, message = "The combination of elite four slot, owner, and open date must be unique for this record.")
public class ChampionOwnershipTermInputDto {

    @NotNull(groups = { ObjectCreation.class })
    private Date openDate;

    @Min(0)
    private Integer wins;

    @Min(0)
    private Integer losses;

    @Min(0)
    private Integer draws;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Member.class)
    private String owner;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Champion.class)
    private String slot;

    private Boolean becomeCurrentOwner = false;

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Boolean getBecomeCurrentOwner() {
        return becomeCurrentOwner;
    }

    public void setBecomeCurrentOwner(Boolean becomeCurrentOwner) {
        this.becomeCurrentOwner = becomeCurrentOwner;
    }
}
