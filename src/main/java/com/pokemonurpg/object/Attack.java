package com.pokemonurpg.object;

import com.pokemonurpg.dto.attack.AttackInputDto;

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

    @OneToOne
    @JoinColumn(name = "category")
    private AttackCategory category;

    @OneToOne
    @JoinColumn(name = "target")
    private AttackTargetType target;

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

    @OneToOne
    @JoinColumn(name = "rse_contest_move_type")
    private RSEContestMoveType rseContestMoveType;

    @OneToOne
    @JoinColumn(name = "rse_contest_attribute")
    private ContestAttribute rseContestAttribute;

    @OneToOne
    @JoinColumn(name = "oras_contest_move_type")
    private ORASContestMoveType orasContestMoveType;

    @OneToOne
    @JoinColumn(name = "oras_contest_attribute")
    private ContestAttribute orasContestAttribute;

    @OneToOne
    @JoinColumn(name = "dpp_contest_move_type")
    private DPPContestMoveType dppContestMoveType;

    @OneToOne
    @JoinColumn(name = "dpp_contest_attribute")
    private ContestAttribute dppContestAttribute;

    @OneToOne
    @JoinColumn(name = "adv_contest_move_type")
    private AdvContestMoveType advContestMoveType;

    @OneToOne
    @JoinColumn(name = "adv_contest_attribute")
    private ContestAttribute advContestAttribute;

    public Attack() { }

    public Attack(String name) {
        this.name = name;
    }

    public Attack(AttackInputDto input) {
        if (input != null) {
            setName(input.getName());
            setDescription(input.getDescription());
            setPower(input.getPower());
            setAccuracy(input.getAccuracy());
            setPp(input.getPp());
            setContact(input.getContact());
            setSnatch(input.getSnatch());
            setSubstitute(input.getSubstitute());
            setSheerForce(input.getSheerForce());
            setMagicCoat(input.getMagicCoat());
        }
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

    public AttackCategory getCategory() {
        return category;
    }

    public void setCategory(AttackCategory category) {
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

    public RSEContestMoveType getRseContestMoveType() {
        return rseContestMoveType;
    }

    public void setRseContestMoveType(RSEContestMoveType rseContestMoveType) {
        this.rseContestMoveType = rseContestMoveType;
    }

    public ContestAttribute getRseContestAttribute() {
        return rseContestAttribute;
    }

    public void setRseContestAttribute(ContestAttribute rseContestAttribute) {
        this.rseContestAttribute = rseContestAttribute;
    }

    public ORASContestMoveType getOrasContestMoveType() {
        return orasContestMoveType;
    }

    public void setOrasContestMoveType(ORASContestMoveType orasContestMoveType) {
        this.orasContestMoveType = orasContestMoveType;
    }

    public ContestAttribute getOrasContestAttribute() {
        return orasContestAttribute;
    }

    public void setOrasContestAttribute(ContestAttribute orasContestAttribute) {
        this.orasContestAttribute = orasContestAttribute;
    }

    public DPPContestMoveType getDppContestMoveType() {
        return dppContestMoveType;
    }

    public void setDppContestMoveType(DPPContestMoveType dppContestMoveType) {
        this.dppContestMoveType = dppContestMoveType;
    }

    public ContestAttribute getDppContestAttribute() {
        return dppContestAttribute;
    }

    public void setDppContestAttribute(ContestAttribute dppContestAttribute) {
        this.dppContestAttribute = dppContestAttribute;
    }

    public AdvContestMoveType getAdvContestMoveType() {
        return advContestMoveType;
    }

    public void setAdvContestMoveType(AdvContestMoveType advContestMoveType) {
        this.advContestMoveType = advContestMoveType;
    }

    public ContestAttribute getAdvContestAttribute() {
        return advContestAttribute;
    }

    public void setAdvContestAttribute(ContestAttribute advContestAttribute) {
        this.advContestAttribute = advContestAttribute;
    }
}
