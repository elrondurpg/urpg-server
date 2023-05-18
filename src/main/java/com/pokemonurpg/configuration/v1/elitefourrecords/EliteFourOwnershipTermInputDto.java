package com.pokemonurpg.configuration.v1.elitefourrecords;

import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.lib.v1.annotations.UniqueId;
import com.pokemonurpg.entities.v1.Member;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@UniqueId(groups = { ObjectCreation.class }, message = "The combination of elite four slot, owner, and open date must be unique for this record.")
public class EliteFourOwnershipTermInputDto {

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
    @ExistsInDb(type = EliteFour.class)
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
