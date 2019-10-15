package com.pokemonurpg.dto.attack;

public class AttackInputDto {

    private String name;
    private String type;
    private String description;
    private Integer power;
    private Integer accuracy;
    private Integer pp;
    private String category;
    private String target;
    private Boolean contact;
    private Boolean snatch;
    private Boolean substitute;
    private Boolean sheerForce;
    private Boolean magicCoat;
    private String rseContestAttribute;
    private String rseContestMoveType;
    private String dppContestAttribute;
    private String dppContestMoveType;
    private String orasContestAttribute;
    private String orasContestMoveType;

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
}
