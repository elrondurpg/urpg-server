package com.pokemonurpg.entities.v1.attack;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.attack.attack.shared.view.GetAttackResponse;
import com.pokemonurpg.configuration.v1.attack.attack.shared.view.ListAttackResponse;
import com.pokemonurpg.entities.v1.contest.ContestAttribute;
import com.pokemonurpg.entities.v1.contest.OrasContestMoveType;
import com.pokemonurpg.entities.v1.contest.RseContestMoveType;
import com.pokemonurpg.entities.v1.item.Item;
import com.pokemonurpg.entities.v1.pokemon.SpeciesAttack;
import com.pokemonurpg.entities.v1.pokemon.Type;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "attack")
public class Attack extends NamedEntity implements ListAttackResponse, GetAttackResponse {

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
    private RseContestMoveType rseContestMoveType;

    @OneToOne
    @JoinColumn(name = "rse_contest_attribute")
    private ContestAttribute rseContestAttribute;

    @OneToOne
    @JoinColumn(name = "oras_contest_move_type")
    private OrasContestMoveType orasContestMoveType;

    @OneToOne
    @JoinColumn(name = "oras_contest_attribute")
    private ContestAttribute orasContestAttribute;

    @OneToOne
    @JoinColumn(name = "tm_hm_dbid")
    private Item tm;

    @OneToMany(mappedBy = "attack")
    private Set<SpeciesAttack> pokemon;

    @OneToMany(mappedBy = "firstAttack")
    private Set<ContestCombo> contestCombos;
}
