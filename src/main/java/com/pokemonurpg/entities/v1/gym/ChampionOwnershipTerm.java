package com.pokemonurpg.entities.v1.gym;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.pokemonurpg.configuration.v1.gym.lib.constants.GymConstants;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.shared.IndexedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChampionOwnershipTerm extends IndexedEntity {

    @OneToOne
    @JoinColumn(name = "slot_dbid")
    private Champion slot;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    private Member owner;

    @Column(name = "open_date")
    private Date openDate;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Integer draws;

    public void setDefaultValues() {
        if (getWins() == null) setWins(GymConstants.DEFAULT_WINS);
        if (getDraws() == null) setDraws(GymConstants.DEFAULT_LOSSES);
        if (getLosses() == null) setLosses(GymConstants.DEFAULT_DRAWS);
    }
}
