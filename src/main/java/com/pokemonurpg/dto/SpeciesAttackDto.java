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
    private Integer pp;
    private String category;
    private String target;
    private Boolean contact;
    private Boolean magicCoat;
    private Boolean sheerForce;
    private Boolean snatch;
    private Boolean substitute;
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
}
