package com.pokemonurpg.dto;

import com.pokemonurpg.object.Attack;
import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.object.Type;

public class SpeciesAttackDto {
    private int dbid;
    private String name;
    private Type type;
    private String description;
    private Integer power;
    private Integer accuracy;
    private int pp;
    private String category;
    private String target;
    private boolean contact;
    private boolean magicCoat;
    private boolean sheerForce;
    private boolean snatch;
    private boolean substitute;
    private String method;
    private Integer generation;

    public SpeciesAttackDto() {    }

    public SpeciesAttackDto(SpeciesAttack sa) {
        if (sa != null) {
            Attack attack = sa.getAttack();
            if (attack != null) {
                dbid = attack.getDbid();
                name = attack.getName();
                type = attack.getType();
                description = attack.getDescription();
                power = attack.getPower();
                accuracy = attack.getAccuracy();
                pp = attack.getPp();
                category = attack.getCategory();
                target = attack.getTarget();
                contact = attack.isContact();
                magicCoat = attack.isMagicCoat();
                sheerForce = attack.isSheerForce();
                snatch = attack.isSnatch();
                substitute = attack.isSubstitute();
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public boolean isMagicCoat() {
        return magicCoat;
    }

    public void setMagicCoat(boolean magicCoat) {
        this.magicCoat = magicCoat;
    }

    public boolean isSheerForce() {
        return sheerForce;
    }

    public void setSheerForce(boolean sheerForce) {
        this.sheerForce = sheerForce;
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
}
