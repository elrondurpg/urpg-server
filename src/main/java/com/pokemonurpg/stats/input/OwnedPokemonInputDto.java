package com.pokemonurpg.stats.input;

import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.general.models.Nature;
import com.pokemonurpg.general.models.Obtained;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.Type;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class OwnedPokemonInputDto extends AuthenticatedInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Member.class)
    private String trainer;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Species.class)
    private String species;

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "^(F|M|G)$")
    private String gender;

    @Pattern(regexp = "^(?!NONE).*$", message = "Nature must not be NONE.")
    @ExistsInDb(type = Nature.class)
    private String nature;

    @Min(0)
    private Integer exp;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Obtained.class)
    private String obtained;

    @Pattern(regexp = "^((https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+(&page=\\d+)?|^(https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+&pid=\\d+#pid\\d+|(https://)?pokemonurpg\\.com/archive/([a-z0-9\\-]+\\.\\d+/)*[a-z0-9\\-]+\\.\\d+(-page-\\d+)?\\.html|(https://)?pokemonurpg\\.com/archive/pxr/(\\d+-[A-Za-z0-9\\-()!]+/)+(page\\d+\\.html)?)$")
    @Size(max = 2083)
    private String obtainedLink;

    @Size(min = 0, max = 34)
    private String nickname;

    @Pattern(regexp = "^(?!NONE|NORMAL|FAIRY).*$", message = "Hidden power type is invalid.")
    @ExistsInDb(type = Type.class)
    private String hiddenPowerType;

    @Pattern(regexp = "^((https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+(&page=\\d+)?|^(https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+&pid=\\d+#pid\\d+|(https://)?pokemonurpg\\.com/archive/([a-z0-9\\-]+\\.\\d+/)*[a-z0-9\\-]+\\.\\d+(-page-\\d+)?\\.html|(https://)?pokemonurpg\\.com/archive/pxr/(\\d+-[A-Za-z0-9\\-()!]+/)+(page\\d+\\.html)?)$")
    @Size(max = 2083)
    private String hiddenPowerLink;

    private List<@Valid OwnedExtraMoveInputDto> ownedExtraMoves = new ArrayList<>();

    private List<@Valid OwnedHiddenAbilityInputDto> ownedHiddenAbilities = new ArrayList<>();

    private List<@Valid EarnedRibbonInputDto> earnedRibbons = new ArrayList<>();

    private Boolean job;

    private Boolean box;

    private Boolean uft;

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
}
