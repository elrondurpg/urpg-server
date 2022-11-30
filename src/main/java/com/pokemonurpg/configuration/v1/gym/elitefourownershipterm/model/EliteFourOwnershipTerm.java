package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.EliteFourOwnershipTermViews;
import com.pokemonurpg.configuration.v1.gym.lib.constants.GymConstants;
import com.pokemonurpg.configuration.v1.lib.model.IndexedConfigurationModel;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.gym.elitefour.EliteFourViews;
import com.pokemonurpg.configuration.v1.gym.elitefour.model.EliteFour;
import com.pokemonurpg.configuration.v1.member.member.model.Member;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonView(value = { EliteFourOwnershipTermViews.Brief.class })
public class EliteFourOwnershipTerm extends IndexedConfigurationModel {

    @OneToOne
    @JoinColumn(name = "slot_dbid")
    @JsonView(value = { EliteFourOwnershipTermViews.Id.class, MemberViews.Full.class })
    private EliteFour slot;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    @JsonView(value = { EliteFourOwnershipTermViews.Id.class, EliteFourViews.Brief.class })
    private Member owner;

    @Column(name = "open_date")
    @JsonView(value = { EliteFourOwnershipTermViews.Id.class, EliteFourViews.Brief.class, MemberViews.Full.class })
    private Date openDate;

    @Column
    @JsonView(value = { EliteFourOwnershipTermViews.Brief.class, MemberViews.Full.class })
    private Integer wins;

    @Column
    @JsonView(value = { EliteFourOwnershipTermViews.Brief.class, MemberViews.Full.class })
    private Integer losses;

    @Column
    @JsonView(value = { EliteFourOwnershipTermViews.Brief.class, MemberViews.Full.class })
    private Integer draws;

    public void setDefaultValues() {
        if (getWins() == null) setWins(GymConstants.DEFAULT_WINS);
        if (getDraws() == null) setDraws(GymConstants.DEFAULT_LOSSES);
        if (getLosses() == null) setLosses(GymConstants.DEFAULT_DRAWS);
    }
}
