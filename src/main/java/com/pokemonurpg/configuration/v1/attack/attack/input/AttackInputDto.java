package com.pokemonurpg.configuration.v1.attack.attack.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pokemonurpg.attack.models.AttackCategory;
import com.pokemonurpg.attack.models.AttackTargetType;
import com.pokemonurpg.configuration.v1.attack.attack.model.Attack;
import com.pokemonurpg.configuration.v1.contest.attribute.model.ContestAttribute;
import com.pokemonurpg.configuration.v1.contest.oras.model.OrasContestMoveType;
import com.pokemonurpg.configuration.v1.contest.rse.model.RseContestMoveType;
import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.item.models.Item;

import lombok.Getter;
import lombok.Setter;

@UniqueName(type = Attack.class)
@Getter
@Setter
public class AttackInputDto extends NamedConfigurationInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 17)
    private String name;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Type.class)
    private String type;

    @Size(min = 3, max = 100)
    private String description;

    @NotNull(groups = { ObjectCreation.class })
    @Min(0)
    @Max(500)
    private Integer power;

    @NotNull(groups = { ObjectCreation.class })
    @Min(0)
    @Max(100)
    private Integer accuracy;

    @NotNull(groups = { ObjectCreation.class })
    @Min(0)
    @Max(100)
    private Integer pp;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = AttackCategory.class)
    private String category;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = AttackTargetType.class)
    private String target;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean contact;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean snatch;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean substitute;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean sheerForce;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean magicCoat;

    @ExistsInDb(type = ContestAttribute.class)
    private String rseContestAttribute;

    @ExistsInDb(type = RseContestMoveType.class)
    private String rseContestMoveType;

    @ExistsInDb(type = ContestAttribute.class)
    private String orasContestAttribute;

    @ExistsInDb(type = OrasContestMoveType.class)
    private String orasContestMoveType;

    @ExistsInDb(type = Item.class)
    private String tm;

    private List<ContestComboInputDto> contestCombos = new ArrayList<>();
}
