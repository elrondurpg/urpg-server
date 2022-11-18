package com.pokemonurpg.configuration.v1.attack.attack.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.attack.models.AttackCategory;
import com.pokemonurpg.attack.models.AttackTargetType;
import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.configuration.v1.contest.attribute.model.ContestAttribute;
import com.pokemonurpg.configuration.v1.contest.oras.model.OrasContestMoveType;
import com.pokemonurpg.configuration.v1.contest.rse.model.RseContestMoveType;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAttack;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.item.models.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonView(value = { AttackViews.Full.class })
public class Attack extends NamedConfigurationModel {

    @OneToOne
    @JoinColumn(name = "type")
    @JsonView(value = { AttackViews.Brief.class, SpeciesViews.Full.class })
    private Type type;

    @Column
    @JsonView(value = { AttackViews.Brief.class, SpeciesViews.Full.class })
    private String description;

    @Column
    @JsonView(value = { AttackViews.Brief.class, SpeciesViews.Full.class })
    private Integer power;

    @Column
    @JsonView(value = { AttackViews.Brief.class, SpeciesViews.Full.class })
    private Integer accuracy;

    @Column
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private Integer pp;

    @OneToOne
    @JoinColumn(name = "category")
    @JsonView(value = { AttackViews.Brief.class, SpeciesViews.Full.class })
    private AttackCategory category;

    @OneToOne
    @JoinColumn(name = "target")
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private AttackTargetType target;

    @Column
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private Boolean contact;

    @Column
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private Boolean snatch;

    @Column
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private Boolean substitute;

    @Column(name = "sheer_force")
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private Boolean sheerForce;

    @Column(name = "magic_coat")
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private Boolean magicCoat;

    @OneToOne
    @JoinColumn(name = "rse_contest_move_type")
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private RseContestMoveType rseContestMoveType;

    @OneToOne
    @JoinColumn(name = "rse_contest_attribute")
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private ContestAttribute rseContestAttribute;

    @OneToOne
    @JoinColumn(name = "oras_contest_move_type")
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
    private OrasContestMoveType orasContestMoveType;

    @OneToOne
    @JoinColumn(name = "oras_contest_attribute")
    @JsonView(value = { AttackViews.Full.class, SpeciesViews.Full.class })
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
}
