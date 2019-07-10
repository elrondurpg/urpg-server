package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
public class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "type")
    private Type type;

    @Column
    private String description;

    @Column
    private Integer power;

    @Column
    private Integer accuracy;

    @Column
    private Integer pp;

    @Column
    private String category;

    @Column
    private String target;

    @Column
    private Boolean contact;

    @Column
    private Boolean snatch;

    @Column
    private Boolean substitute;

    @Column(name = "sheer_force")
    private Boolean sheerForce;

    @Column(name = "magic_coat")
    private Boolean magicCoat;

    public Attack() { }

    public Attack(Integer dbid, String name, Type type, String description, int power, int accuracy, int pp, String category, String target, boolean contact, boolean snatch, boolean substitute, boolean sheerForce, boolean magicCoat) {
        this.dbid = dbid;
        this.name = name;
        this.type = type;
        this.description = description;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.category = category;
        this.target = target;
        this.contact = contact;
        this.snatch = snatch;
        this.substitute = substitute;
        this.sheerForce = sheerForce;
        this.magicCoat = magicCoat;
    }

    public void cloneValuesFrom(Attack attack) {
        if (dbid == null) this.dbid = attack.getDbid();
        if (name == null) this.name = attack.getName();
        if (type == null) this.type = attack.getType();
        if (description == null) this.description = attack.getDescription();
        if (power == null) this.power = attack.getPower();
        if (accuracy == null) this.accuracy = attack.getAccuracy();
        if (pp == null) this.pp = attack.getPp();
        if (category == null) this.category = attack.getCategory();
        if (target == null) this.target = attack.getTarget();
        if (contact == null) this.contact = attack.isContact();
        if (snatch == null) this.snatch = attack.isSnatch();
        if (substitute == null) this.substitute = attack.isSubstitute();
        if (sheerForce == null) this.sheerForce = attack.isSheerForce();
        if (magicCoat == null) this.magicCoat = attack.isMagicCoat();
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
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

    public Boolean isSheerForce() {
        return sheerForce;
    }

    public void setSheerForce(Boolean sheerForce) {
        this.sheerForce = sheerForce;
    }

    public Boolean isMagicCoat() {
        return magicCoat;
    }

    public void setMagicCoat(Boolean magicCoat) {
        this.magicCoat = magicCoat;
    }
}
