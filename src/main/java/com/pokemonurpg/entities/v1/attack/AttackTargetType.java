package com.pokemonurpg.entities.v1.attack;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.pokemon.species.shared.view.GetSpeciesAttackTargetTypeView;
import com.pokemonurpg.configuration.v2.attack.attack.shared.view.AttackResponseTargetPart;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "attack_target_type")
@Getter
@Setter
public class AttackTargetType extends NamedEntity implements AttackResponseTargetPart, GetSpeciesAttackTargetTypeView {

    @Column
    private String description;
}
