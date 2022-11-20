package com.pokemonurpg.configuration.v1.member.member.input;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.DoesNotConflictWithKnownGymLeader;
import com.pokemonurpg.core.validation.annotation.UniqueDiscordId;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

@UniqueName(type = Member.class)
@UniqueDiscordId
@Getter
@Setter
public class MemberInputDto extends NamedConfigurationInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "\\d+")
    private String discordId;

    @NotNull(groups = { ObjectCreation.class })
    @DoesNotConflictWithKnownGymLeader
    @Size(min = 3, max = 30)
    private String name;

    private Integer money;

    @Min(0)
    private Integer wins;

    @Min(0)
    private Integer losses;

    @Min(0)
    private Integer draws;

    @NotNull(groups = { ObjectCreation.class })
    private Date joinDate;

    private List<@Valid MemberRoleInputDto> roles = new ArrayList<>();

    private List<@Valid LegendaryProgressInputDto> legendaryProgress = new ArrayList<>();

    private List<@Valid OwnedItemInputDto> items = new ArrayList<>();

    private Boolean bot;

    private List<@Valid EliteFourVictoryInputDto> eliteFourVictories = new ArrayList<>();

    private List<@Valid ChampionVictoryInputDto> championVictories = new ArrayList<>();

    private List<@Valid GymVictoryInputDto> gymVictories = new ArrayList<>();
}
