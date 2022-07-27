package com.pokemonurpg.stats.input;


import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.general.models.Nature;
import com.pokemonurpg.general.models.Obtained;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.strings.GeneralConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OwnedPokemonInputDto {

    @ExistsInDb(type = Member.class)
    private String trainer;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Species.class)
    protected String species;

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "^(F|M|G)$")
    protected String gender;

    @Pattern(regexp = "^(?!NONE).*$", message = "Nature must not be NONE.")
    @ExistsInDb(type = Nature.class)
    protected String nature;

    @Min(0)
    protected Integer exp;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Obtained.class)
    protected String obtained;

    @Pattern(regexp = GeneralConstants.ALLOWED_URL_PATTERN)
    @Size(max = 2083)
    protected String obtainedLink;

    @Size(min = 0, max = 34)
    protected String nickname;

    @Pattern(regexp = "^(?!NONE|NORMAL|FAIRY).*$", message = "Hidden power type is invalid.")
    @ExistsInDb(type = Type.class)
    protected String hiddenPowerType;

    @Pattern(regexp = GeneralConstants.ALLOWED_URL_PATTERN)
    @Size(max = 2083)
    protected String hiddenPowerLink;

    protected List<@Valid OwnedExtraMoveInputDto> ownedExtraMoves = new ArrayList<>();

    protected List<@Valid OwnedHiddenAbilityInputDto> ownedHiddenAbilities = new ArrayList<>();

    protected List<@Valid EarnedRibbonInputDto> earnedRibbons = new ArrayList<>();

    protected Boolean job;

    protected Boolean box;

    protected Boolean uft;

    protected Boolean rental;

    protected Set<@Valid WishlistAbilityInputDto> wishlistAbilities = new HashSet<>();

    protected Set<@Valid WishlistMoveInputDto> wishlistMoves = new HashSet<>();

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getObtained() {
        return obtained;
    }

    public void setObtained(String obtained) {
        this.obtained = obtained;
    }

    public String getObtainedLink() {
        return obtainedLink;
    }

    public void setObtainedLink(String obtainedLink) {
        this.obtainedLink = obtainedLink;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHiddenPowerType() {
        return hiddenPowerType;
    }

    public void setHiddenPowerType(String hiddenPowerType) {
        this.hiddenPowerType = hiddenPowerType;
    }

    public String getHiddenPowerLink() {
        return hiddenPowerLink;
    }

    public void setHiddenPowerLink(String hiddenPowerLink) {
        this.hiddenPowerLink = hiddenPowerLink;
    }

    public List<OwnedExtraMoveInputDto> getOwnedExtraMoves() {
        return ownedExtraMoves;
    }

    public void setOwnedExtraMoves(List<OwnedExtraMoveInputDto> ownedExtraMoves) {
        this.ownedExtraMoves = ownedExtraMoves;
    }

    public List<OwnedHiddenAbilityInputDto> getOwnedHiddenAbilities() {
        return ownedHiddenAbilities;
    }

    public void setOwnedHiddenAbilities(List<OwnedHiddenAbilityInputDto> ownedHiddenAbilities) {
        this.ownedHiddenAbilities = ownedHiddenAbilities;
    }

    public List<EarnedRibbonInputDto> getEarnedRibbons() {
        return earnedRibbons;
    }

    public void setEarnedRibbons(List<EarnedRibbonInputDto> earnedRibbons) {
        this.earnedRibbons = earnedRibbons;
    }

    public Boolean getJob() {
        return job;
    }

    public void setJob(Boolean job) {
        this.job = job;
    }

    public Boolean getBox() {
        return box;
    }

    public void setBox(Boolean box) {
        this.box = box;
    }

    public Boolean getUft() {
        return uft;
    }

    public void setUft(Boolean uft) {
        this.uft = uft;
    }

    public Boolean getRental() {
        return rental;
    }

    public void setRental(Boolean rental) {
        this.rental = rental;
    }

    public Set<WishlistAbilityInputDto> getWishlistAbilities() {
        return wishlistAbilities;
    }

    public void setWishlistAbilities(Set<WishlistAbilityInputDto> wishlistAbilities) {
        this.wishlistAbilities = wishlistAbilities;
    }

    public Set<WishlistMoveInputDto> getWishlistMoves() {
        return wishlistMoves;
    }

    public void setWishlistMoves(Set<WishlistMoveInputDto> wishlistMoves) {
        this.wishlistMoves = wishlistMoves;
    }

    
}
