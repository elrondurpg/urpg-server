package com.pokemonurpg.configuration.v1.pokemon.species.input;

import com.pokemonurpg.entities.v1.creative.ArtRank;
import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.entities.v1.pokemon.Type;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.*;
import com.pokemonurpg.entities.v1.creative.ParkLocation;
import com.pokemonurpg.entities.v1.creative.ParkRank;
import com.pokemonurpg.entities.v1.creative.StoryRank;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@UniqueName(type = Species.class)
@Getter
@Setter
public class SpeciesInputDto extends NamedConfigurationInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    private Integer dexno;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 21)
    private String name;

    @Size(min = 3, max = 20)
    private String displayName;

    @Size(min = 3, max = 20)
    private String formName;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Type.class)
    @Pattern(regexp = "^(?!NONE).*$", message = "Type 1 must not be NONE.")
    private String type1;

    @ExistsInDb(type = Type.class)
    private String type2;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String classification;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer hp;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer attack;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer defense;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer specialAttack;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer specialDefense;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer speed;

    @NotNull(groups = { ObjectCreation.class })
    @DecimalMin("0.01")
    @DecimalMax("1000000")
    private Double height;

    @NotNull(groups = { ObjectCreation.class })
    @DecimalMin("0.01")
    @DecimalMax("1000000")
    private Double weight;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean maleAllowed;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean femaleAllowed;

    @Min(0)
    @Max(20000)
    private Integer pokemart;

    @Min(0)
    @Max(100000)
    private Integer contestCredits;

    @ExistsInDb(type = StoryRank.class)
    private String storyRank;

    @ExistsInDb(type = ArtRank.class)
    private String artRank;

    @ExistsInDb(type = ParkRank.class)
    private String parkRank;

    @ExistsInDb(type = ParkLocation.class)
    private String parkLocation;

    private List<@Valid SpeciesAttackInputDto> attacks = new ArrayList<>();

    private List<@Valid SpeciesAbilityInputDto> abilities = new ArrayList<>();

    private List<@Valid CosmeticFormInputDto> cosmeticForms = new ArrayList<>();

    @Size(min = 0, max = 100)
    private String alteredFormMethod;

    @ExistsInDb(type = Species.class)
    private String preEvolution;

    @Size(min = 3, max = 50)
    private String evolutionMethod;

    @Min(5)
    @Max(7)
    private Integer evolutionExpRequirement;

    @ExistsInDb(type = Species.class)
    private String preMega;

    @Size(min = 3, max = 20)
    private String megaStone;

    @Size(min = 3, max = 7)
    private String megaSuffix;

    @Min(0)
    @Max(2)
    private Integer legendaryTier;

    private Boolean battleOnly;    
}
