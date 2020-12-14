package com.pokemonurpg.attack.input;

import com.pokemonurpg.attack.models.AttackCategory;
import com.pokemonurpg.attack.models.AttackTargetType;
import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.models.DPPContestMoveType;
import com.pokemonurpg.contest.models.ORASContestMoveType;
import com.pokemonurpg.contest.models.RSEContestMoveType;
import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.species.models.Type;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AttackInputDto extends AuthenticatedInputDto {
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
    private String dppContestAttribute;

    @ExistsInDb(type = DPPContestMoveType.class)
    private String dppContestMoveType;

    @ExistsInDb(type = ContestAttribute.class)
    private String orasContestAttribute;

    @ExistsInDb(type = ORASContestMoveType.class)
    private String orasContestMoveType;

    private Integer tmHmDbid;

    /*@ExistsInDb(type = ContestAttribute.class)
    private String advContestAttribute;
    private String advContestMoveType;*/

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

    public String getDppContestAttribute() {
        return dppContestAttribute;
    }

    public void setDppContestAttribute(String dppContestAttribute) {
        this.dppContestAttribute = dppContestAttribute;
    }

    public String getDppContestMoveType() {
        return dppContestMoveType;
    }

    public void setDppContestMoveType(String dppContestMoveType) {
        this.dppContestMoveType = dppContestMoveType;
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

    public Integer getTmHmDbid() {
        return tmHmDbid;
    }

    public void setTmHmDbid(Integer tmHmDbid) {
        this.tmHmDbid = tmHmDbid;
    }
}