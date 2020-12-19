package com.pokemonurpg.gym.input;

import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GymInputDto extends AuthenticatedInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String name;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Member.class)
    private String owner;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = GymLeague.class)
    private String league;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Badge.class)
    private String badge;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean active;

    @NotNull(groups = { ObjectCreation.class })
    private Date openDate;

    @Min(0)
    private Integer wins;

    @Min(0)
    private Integer losses;

    @Min(0)
    private Integer draws;

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "TM\\d+.+")
    @ExistsInDb(type = Item.class)
    private String tm;

    private List<@Valid GymPokemonInputDto> pokemon = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

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

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public List<GymPokemonInputDto> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<GymPokemonInputDto> pokemon) {
        this.pokemon = pokemon;
    }
}
