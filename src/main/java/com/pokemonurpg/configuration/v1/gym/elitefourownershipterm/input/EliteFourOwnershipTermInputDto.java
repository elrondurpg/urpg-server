package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.input;

import com.pokemonurpg.configuration.v1.lib.input.ConfigurationInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.core.validation.annotation.UniqueId;
import com.pokemonurpg.configuration.v1.gym.elitefour.model.EliteFour;
import com.pokemonurpg.member.models.Member;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@UniqueId(groups = { ObjectCreation.class }, message = "The combination of elite four slot, owner, and open date must be unique for this record.")
@Getter
@Setter
public class EliteFourOwnershipTermInputDto extends ConfigurationInputDto {

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
}
