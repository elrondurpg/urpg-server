package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.attacks.AttackInputDto;
import com.pokemonurpg.lib.v1.models.NamedObject;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Attack implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
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
    @JoinColumn(name = "tm_hm_dbid")
    private Item tm;

    @OneToMany(mappedBy = "attack")
    @JsonIgnoreProperties({ "attack" })
    private Set<SpeciesAttack> pokemon;

    @OneToMany(mappedBy = "firstAttack")
    @JsonIgnoreProperties({ "firstAttack" })
    private Set<ContestCombo> contestCombos;

    public Attack() { }

    public Attack (AttackInputDto input) {
        this.update(input);
    }

    public void update(AttackInputDto input) {
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

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        if (dbid != null) {
            this.dbid = dbid;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {

        if (type != null) {
            this.type = type;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        if (description != null) {
            this.description = description;
        }
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        if (power != null) {
            this.power = power;
        }
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        if (accuracy != null) {
            this.accuracy = accuracy;
        }
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        if (pp != null) {
            this.pp = pp;
        }
    }

    public AttackCategory getCategory() {
        return category;
    }

    public void setCategory(AttackCategory category) {
        if (category != null) {
            this.category = category;
        }
    }

    public AttackTargetType getTarget() {
        return target;
    }

    public void setTarget(AttackTargetType target) {
        if (target != null) {
            this.target = target;
        }
    }

    public Boolean isContact() {
        return contact;
    }

    public void setContact(Boolean contact) {

        if (contact != null) {
            this.contact = contact;
        }
    }

    public Boolean isSnatch() {
        return snatch;
    }

    public void setSnatch(Boolean snatch) {
        if (snatch != null) {
            this.snatch = snatch;
        }
    }

    public Boolean isSubstitute() {
        return substitute;
    }

    public void setSubstitute(Boolean substitute) {
        if (substitute != null) {
            this.substitute = substitute;
        }
    }

    public Boolean isSheerForce() {
        return sheerForce;
    }

    public void setSheerForce(Boolean sheerForce) {
        if (sheerForce != null) {
            this.sheerForce = sheerForce;
        }
    }

    public Boolean isMagicCoat() {
        return magicCoat;
    }

    public void setMagicCoat(Boolean magicCoat) {
        if (magicCoat != null) {
            this.magicCoat = magicCoat;
        }
    }

    public RSEContestMoveType getRseContestMoveType() {
        return rseContestMoveType;
    }

    public void setRseContestMoveType(RSEContestMoveType rseContestMoveType) {
        if (rseContestMoveType != null ) {
            this.rseContestMoveType = rseContestMoveType;
        }
    }

    public ContestAttribute getRseContestAttribute() {
        return rseContestAttribute;
    }

    public void setRseContestAttribute(ContestAttribute rseContestAttribute) {
        if (rseContestAttribute != null) {
            this.rseContestAttribute = rseContestAttribute;
        }
    }

    public ORASContestMoveType getOrasContestMoveType() {
        return orasContestMoveType;
    }

    public void setOrasContestMoveType(ORASContestMoveType orasContestMoveType) {
        if (orasContestMoveType != null) {
            this.orasContestMoveType = orasContestMoveType;
        }
    }

    public ContestAttribute getOrasContestAttribute() {
        return orasContestAttribute;
    }

    public void setOrasContestAttribute(ContestAttribute orasContestAttribute) {
        if (orasContestAttribute != null) {
            this.orasContestAttribute = orasContestAttribute;
        }
    }

    public Item getTm() {
        return tm;
    }

    public void setTm(Item tm) {
        this.tm = tm;
    }

    public Set<SpeciesAttack> getPokemon() {
        return pokemon;
    }

    public void setPokemon(Set<SpeciesAttack> pokemon) {
        this.pokemon = pokemon;
    }

    public Set<ContestCombo> getContestCombos() {
        return contestCombos;
    }

    public void setContestCombos(Set<ContestCombo> contestCombos) {
        this.contestCombos = contestCombos;
    }
}
