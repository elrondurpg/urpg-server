package com.pokemonurpg.attack.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.attack.input.AttackInputDto;
import com.pokemonurpg.configuration.v1.lib.ConfigurationViews;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAttack;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.contest.models.*;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.item.models.Item;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Attack implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonView(value = { SpeciesViews.Full.class })
    private Integer dbid;

    @Column
    @JsonView(value = { SpeciesViews.Full.class })
    private String name;

    @OneToOne
    @JoinColumn(name = "type")
    @JsonView(value = { SpeciesViews.Full.class })
    private Type type;

    @Column
    @JsonView(value = { SpeciesViews.Full.class })
    private String description;

    @Column
    @JsonView(value = { SpeciesViews.Full.class })
    private Integer power;

    @Column
    @JsonView(value = { SpeciesViews.Full.class })
    private Integer accuracy;

    @Column
    @JsonView(value = { SpeciesViews.Full.class })
    private Integer pp;

    @OneToOne
    @JoinColumn(name = "category")
    @JsonView(value = { SpeciesViews.Full.class })
    private AttackCategory category;

    @OneToOne
    @JoinColumn(name = "target")
    @JsonView(value = { SpeciesViews.Full.class })
    private AttackTargetType target;

    @Column
    @JsonView(value = { SpeciesViews.Full.class })
    private Boolean contact;

    @Column
    @JsonView(value = { SpeciesViews.Full.class })
    private Boolean snatch;

    @Column
    @JsonView(value = { SpeciesViews.Full.class })
    private Boolean substitute;

    @Column(name = "sheer_force")
    @JsonView(value = { SpeciesViews.Full.class })
    private Boolean sheerForce;

    @Column(name = "magic_coat")
    @JsonView(value = { SpeciesViews.Full.class })
    private Boolean magicCoat;

    @OneToOne
    @JoinColumn(name = "rse_contest_move_type")
    @JsonView(value = { SpeciesViews.Full.class })
    private RSEContestMoveType rseContestMoveType;

    @OneToOne
    @JoinColumn(name = "rse_contest_attribute")
    @JsonView(value = { SpeciesViews.Full.class })
    private ContestAttribute rseContestAttribute;

    @OneToOne
    @JoinColumn(name = "oras_contest_move_type")
    @JsonView(value = { SpeciesViews.Full.class })
    private ORASContestMoveType orasContestMoveType;

    @OneToOne
    @JoinColumn(name = "oras_contest_attribute")
    @JsonView(value = { SpeciesViews.Full.class })
    private ContestAttribute orasContestAttribute;

    @OneToOne
    @JoinColumn(name = "dpp_contest_move_type")
    @JsonView(value = { SpeciesViews.Full.class })
    private DPPContestMoveType dppContestMoveType;

    @OneToOne
    @JoinColumn(name = "dpp_contest_attribute")
    @JsonView(value = { SpeciesViews.Full.class })
    private ContestAttribute dppContestAttribute;

    @OneToOne
    @JoinColumn(name = "adv_contest_move_type")
    @JsonView(value = { SpeciesViews.Full.class })
    private AdvContestMoveType advContestMoveType;

    @OneToOne
    @JoinColumn(name = "adv_contest_attribute")
    @JsonView(value = { SpeciesViews.Full.class })
    private ContestAttribute advContestAttribute;

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

    public DPPContestMoveType getDppContestMoveType() {
        return dppContestMoveType;
    }

    public void setDppContestMoveType(DPPContestMoveType dppContestMoveType) {
        if (dppContestMoveType != null) {
            this.dppContestMoveType = dppContestMoveType;
        }
    }

    public ContestAttribute getDppContestAttribute() {
        return dppContestAttribute;
    }

    public void setDppContestAttribute(ContestAttribute dppContestAttribute) {
        if (dppContestAttribute != null) {
            this.dppContestAttribute = dppContestAttribute;
        }
    }

    public AdvContestMoveType getAdvContestMoveType() {
        return advContestMoveType;
    }

    public void setAdvContestMoveType(AdvContestMoveType advContestMoveType) {
        if (advContestMoveType != null) {
            this.advContestMoveType = advContestMoveType;
        }
    }

    public ContestAttribute getAdvContestAttribute() {
        return advContestAttribute;
    }

    public void setAdvContestAttribute(ContestAttribute advContestAttribute) {
        if (advContestAttribute != null) {
            this.advContestAttribute = advContestAttribute;
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
