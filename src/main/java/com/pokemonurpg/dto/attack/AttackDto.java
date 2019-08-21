package com.pokemonurpg.dto.attack;

import com.pokemonurpg.object.Attack;
import com.pokemonurpg.object.AttackTargetType;

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
}
