package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.EliteFourOwnershipTermViews;
import com.pokemonurpg.configuration.v1.lib.model.IndexedConfigurationModel;
import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.member.models.Member;

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
    @JsonView(value = { EliteFourOwnershipTermViews.Id.class })
    private EliteFour slot;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    @JsonView(value = { EliteFourOwnershipTermViews.Id.class })
    private Member owner;

    @Column(name = "open_date")
    @JsonView(value = { EliteFourOwnershipTermViews.Id.class })
    private Date openDate;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Integer draws;
}
