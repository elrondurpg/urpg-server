package com.pokemonurpg.configuration.v1.gym.gymownershipterm.input;

import com.pokemonurpg.configuration.v1.lib.input.ConfigurationInputDto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.core.validation.annotation.UniqueId;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.configuration.v1.member.member.model.Member;

import lombok.Getter;
import lombok.Setter;

@UniqueId(groups = { ObjectCreation.class }, message = "The combination of gym, owner, and open date must be unique for this record.")
@Getter
@Setter
public class GymOwnershipTermInputDto extends ConfigurationInputDto {

    @NotNull(groups = { ObjectCreation.class })
    private Date openDate;

    @Min(0)
    private Integer wins;

    @Min(0)
    private Integer losses;

    @Min(0)
    private Integer draws;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Item.class)
    private String tm;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Member.class)
    private String owner;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = League.class)
    private String league;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Gym.class)
    private String gym;

    private Boolean becomeCurrentOwner = false;
}
