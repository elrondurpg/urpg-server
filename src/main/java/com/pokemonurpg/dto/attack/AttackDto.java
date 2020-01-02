package com.pokemonurpg.dto.attack;

import com.pokemonurpg.object.pokemon.*;

public class AttackDto {
    private int dbid;
    private String name;
    private String type;
    private String description;
    private int power;
    private int accuracy;
    private int pp;
    private String category;
    private AttackTargetType target;
    private boolean contact;
    private boolean snatch;
    private boolean substitute;
    private boolean sheerForce;
    private boolean magicCoat;
    private String rseContestAttribute;
    private RSEContestMoveType rseContestMoveType;
    private String dppContestAttribute;
    private DPPContestMoveType dppContestMoveType;
    private String orasContestAttribute;
    private ORASContestMoveType orasContestMoveType;

    public AttackDto(Attack attack) {
        if (attack != null) {
            setDbid(attack.getDbid());
            setName(attack.getName());
            if (attack.getType() != null) {
                setType(attack.getType().getName());
            }
            setDescription(attack.getDescription());
            setPower(attack.getPower());
            setAccuracy(attack.getAccuracy());
            setPp(attack.getPp());
            if (attack.getCategory() != null) {
                setCategory(attack.getCategory().getName());
            }
            setTarget(attack.getTarget());
            setContact(attack.isContact());
            setSnatch(attack.isSnatch());
            setSubstitute(attack.isSubstitute());
            setSheerForce(attack.isSheerForce());
            setMagicCoat(attack.isMagicCoat());
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

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
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

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public boolean isSnatch() {
        return snatch;
    }

    public void setSnatch(boolean snatch) {
        this.snatch = snatch;
    }

    public boolean isSubstitute() {
        return substitute;
    }

    public void setSubstitute(boolean substitute) {
        this.substitute = substitute;
    }

    public boolean isSheerForce() {
        return sheerForce;
    }

    public void setSheerForce(boolean sheerForce) {
        this.sheerForce = sheerForce;
    }

    public boolean isMagicCoat() {
        return magicCoat;
    }

    public void setMagicCoat(boolean magicCoat) {
        this.magicCoat = magicCoat;
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
