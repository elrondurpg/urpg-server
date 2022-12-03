package com.pokemonurpg.entities.v3.attack;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesFullResponseAttackView;
import com.pokemonurpg.entities.v3.contest.ContestAttributeEntity;
import com.pokemonurpg.entities.v3.contest.OrasContestMoveTypeEntity;
import com.pokemonurpg.entities.v3.contest.RseContestMoveTypeEntity;
import com.pokemonurpg.entities.v3.item.ItemEntity;
import com.pokemonurpg.entities.v3.pokemon.species.SpeciesAttackEntity;
import com.pokemonurpg.entities.v3.pokemon.type.TypeEntity;
import com.pokemonurpg.entities.v3.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "attack")
public class AttackEntity extends NamedEntity implements SpeciesFullResponseAttackView {

    @OneToOne
    @JoinColumn(name = "type")
    private TypeEntity type;

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
    private AttackCategoryEntity category;

    @OneToOne
    @JoinColumn(name = "target")
    private AttackTargetTypeEntity target;

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
    private RseContestMoveTypeEntity rseContestMoveType;

    @OneToOne
    @JoinColumn(name = "rse_contest_attribute")
    private ContestAttributeEntity rseContestAttribute;

    @OneToOne
    @JoinColumn(name = "oras_contest_move_type")
    private OrasContestMoveTypeEntity orasContestMoveType;

    @OneToOne
    @JoinColumn(name = "oras_contest_attribute")
    private ContestAttributeEntity orasContestAttribute;

    @OneToOne
    @JoinColumn(name = "tm_hm_dbid")
    private ItemEntity tm;

    @OneToMany(mappedBy = "attack")
    private Set<SpeciesAttackEntity> pokemon;

    @OneToMany(mappedBy = "firstAttack")
    private Set<ContestComboEntity> contestCombos;
}
