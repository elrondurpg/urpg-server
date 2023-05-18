package com.pokemonurpg.configuration.v1.attacks;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.entities.v1.AttackCategory;
import com.pokemonurpg.entities.v1.AttackTargetType;
import com.pokemonurpg.entities.v1.ContestAttribute;
import com.pokemonurpg.entities.v1.ORASContestMoveType;
import com.pokemonurpg.entities.v1.RSEContestMoveType;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedInputDto;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.lib.v1.annotations.UniqueName;
import com.pokemonurpg.entities.v1.Item;
import com.pokemonurpg.entities.v1.Type;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@UniqueName(type = Attack.class)
public class AttackInputDto implements UniquelyNamedInputDto {
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

    @ExistsInDb(type = RSEContestMoveType.class)
    private String rseContestMoveType;

    @ExistsInDb(type = ContestAttribute.class)
    private String orasContestAttribute;

    @ExistsInDb(type = ORASContestMoveType.class)
    private String orasContestMoveType;

    @ExistsInDb(type = Item.class)
    private String tm;

    private List<@Valid ContestComboInputDto> contestCombos = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Boolean getContact() {
        return contact;
    }

    public void setContact(Boolean contact) {
        this.contact = contact;
    }

    public Boolean getSnatch() {
        return snatch;
    }

    public void setSnatch(Boolean snatch) {
        this.snatch = snatch;
    }

    public Boolean getSubstitute() {
        return substitute;
    }

    public void setSubstitute(Boolean substitute) {
        this.substitute = substitute;
    }

    public Boolean getSheerForce() {
        return sheerForce;
    }

    public void setSheerForce(Boolean sheerForce) {
        this.sheerForce = sheerForce;
    }

    public Boolean getMagicCoat() {
        return magicCoat;
    }

    public void setMagicCoat(Boolean magicCoat) {
        this.magicCoat = magicCoat;
    }

    public String getRseContestAttribute() {
        return rseContestAttribute;
    }

    public void setRseContestAttribute(String rseContestAttribute) {
        this.rseContestAttribute = rseContestAttribute;
    }

    public String getRseContestMoveType() {
        return rseContestMoveType;
    }

    public void setRseContestMoveType(String rseContestMoveType) {
        this.rseContestMoveType = rseContestMoveType;
    }

    public String getOrasContestAttribute() {
        return orasContestAttribute;
    }

    public void setOrasContestAttribute(String orasContestAttribute) {
        this.orasContestAttribute = orasContestAttribute;
    }

    public String getOrasContestMoveType() {
        return orasContestMoveType;
    }

    public void setOrasContestMoveType(String orasContestMoveType) {
        this.orasContestMoveType = orasContestMoveType;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public List<ContestComboInputDto> getContestCombos() {
        return contestCombos;
    }

    public void setContestCombos(List<ContestComboInputDto> contestCombos) {
        this.contestCombos = contestCombos;
    }
}
