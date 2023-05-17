package com.pokemonurpg.dto.species.response;

import com.pokemonurpg.object.*;

public class SpeciesAttackDto {
    private int dbid;
    private String name;
    private String type;
    private String description;
    private Integer power;
    private Integer accuracy;
    private Integer pp;
    private String category;
    private AttackTargetType target;
    private Boolean contact;
    private Boolean magicCoat;
    private Boolean sheerForce;
    private Boolean snatch;
    private Boolean substitute;
    private String method;
    private Integer generation;
    private String rseContestAttribute;
    private RSEContestMoveType rseContestMoveType;
    private String dppContestAttribute;
    private DPPContestMoveType dppContestMoveType;
    private String orasContestAttribute;
    private ORASContestMoveType orasContestMoveType;

    public SpeciesAttackDto() {    }

    public SpeciesAttackDto(SpeciesAttack sa) {
        if (sa != null) {
            Attack attack = sa.getAttack();
            if (attack != null) {
                dbid = attack.getDbid();
                name = attack.getName();
                if (attack.getType() != null) {
                    type = attack.getType().getName();
                }
                description = attack.getDescription();
                power = attack.getPower();
                accuracy = attack.getAccuracy();
                pp = attack.getPp();
                if (attack.getCategory() != null) {
                    category = attack.getCategory().getName();
                }
                target = attack.getTarget();
                contact = attack.isContact();
                magicCoat = attack.isMagicCoat();
                sheerForce = attack.isSheerForce();
                snatch = attack.isSnatch();
                substitute = attack.isSubstitute();
                if (attack.getRseContestAttribute() != null) {
                    setRseContestAttribute(attack.getRseContestAttribute().getName());
                }
                if (attack.getRseContestMoveType() != null) {
                    setRseContestMoveType(attack.getRseContestMoveType());
                }
                if (attack.getDppContestAttribute() != null) {
                    setDppContestAttribute(attack.getDppContestAttribute().getName());
                }
                if (attack.getDppContestMoveType() != null) {
                    setDppContestMoveType(attack.getDppContestMoveType());
                }
                if (attack.getOrasContestAttribute() != null) {
                    setOrasContestAttribute(attack.getOrasContestAttribute().getName());
                }
                if (attack.getOrasContestMoveType() != null) {
                    setOrasContestMoveType(attack.getOrasContestMoveType());
                }
            }
            method = sa.getMethod();
            generation = sa.getGeneration();
        }
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

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

    public AttackTargetType getTarget() {
        return target;
    }

    public void setTarget(AttackTargetType target) {
        this.target = target;
    }

    public Boolean isContact() {
        return contact;
    }

    public void setContact(Boolean contact) {
        this.contact = contact;
    }

    public Boolean isMagicCoat() {
        return magicCoat;
    }

    public void setMagicCoat(Boolean magicCoat) {
        this.magicCoat = magicCoat;
    }

    public Boolean isSheerForce() {
        return sheerForce;
    }

    public void setSheerForce(Boolean sheerForce) {
        this.sheerForce = sheerForce;
    }

    public Boolean isSnatch() {
        return snatch;
    }

    public void setSnatch(Boolean snatch) {
        this.snatch = snatch;
    }

    public Boolean isSubstitute() {
        return substitute;
    }

    public void setSubstitute(Boolean substitute) {
        this.substitute = substitute;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public String getRseContestAttribute() {
        return rseContestAttribute;
    }

    public void setRseContestAttribute(String rseContestAttribute) {
        this.rseContestAttribute = rseContestAttribute;
    }

    public RSEContestMoveType getRseContestMoveType() {
        return rseContestMoveType;
    }

    public void setRseContestMoveType(RSEContestMoveType rseContestMoveType) {
        this.rseContestMoveType = rseContestMoveType;
    }

    public String getDppContestAttribute() {
        return dppContestAttribute;
    }

    public void setDppContestAttribute(String dppContestAttribute) {
        this.dppContestAttribute = dppContestAttribute;
    }

    public DPPContestMoveType getDppContestMoveType() {
        return dppContestMoveType;
    }

    public void setDppContestMoveType(DPPContestMoveType dppContestMoveType) {
        this.dppContestMoveType = dppContestMoveType;
    }

    public String getOrasContestAttribute() {
        return orasContestAttribute;
    }

    public void setOrasContestAttribute(String orasContestAttribute) {
        this.orasContestAttribute = orasContestAttribute;
    }

    public ORASContestMoveType getOrasContestMoveType() {
        return orasContestMoveType;
    }

    public void setOrasContestMoveType(ORASContestMoveType orasContestMoveType) {
        this.orasContestMoveType = orasContestMoveType;
    }
}
