package com.pokemonurpg.entities.v1.stats;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.pokemon.Ability;
import com.pokemonurpg.entities.v1.pokemon.CaptureMethod;
import com.pokemonurpg.entities.v1.pokemon.Nature;
import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.entities.v1.pokemon.Type;
import com.pokemonurpg.entities.v1.shared.IndexedEntity;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "owned_pokemon")
@Getter
@Setter
public class OwnedPokemon extends IndexedEntity {

    @ManyToOne
    @JoinColumn(name="trainer_dbid", nullable=false)
    private Member trainer;

    @OneToOne
    @JoinColumn(name = "species_dbid")
    private Species species;

    @Column
    private String gender;

    @OneToOne
    @JoinColumn(name = "nature")
    private Nature nature;

    @Column
    private Integer exp = 0;

    @OneToOne
    @JoinColumn(name = "obtained")
    private CaptureMethod obtained;

    @Column(name = "obtained_link")
    private String obtainedLink;

    @Column
    private String nickname;

    @OneToOne
    @JoinColumn(name = "hidden_power_type")
    private Type hiddenPowerType;

    @Column(name = "hidden_power_type_link")
    private String hiddenPowerLink;

    @ManyToMany(
            targetEntity=Ability.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="OWNED_HIDDEN_ABILITY",
            joinColumns=@JoinColumn(name="POKEMON_DBID"),
            inverseJoinColumns=@JoinColumn(name="ABILITY_DBID")
    )
    private Set<Ability> ownedHiddenAbilities;

    @ManyToMany(
            targetEntity=Attack.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="OWNED_EXTRA_MOVE",
            joinColumns=@JoinColumn(name="POKEMON_DBID"),
            inverseJoinColumns=@JoinColumn(name="ATTACK_DBID")
    )
    private Set<Attack> ownedExtraMoves;

    @OneToMany(mappedBy="pokemon")
    private List<EarnedRibbon> earnedRibbons;

    @Column
    private Boolean job;

    @Column
    private Boolean box;

    @Column
    private Boolean uft;

    @Column
    private Boolean rental;

    @OneToMany(mappedBy="pokemon")
    private Set<WishlistAbility> wishlistAbilities;

    @OneToMany(mappedBy="pokemon")
    private Set<WishlistMove> wishlistMoves;

    public OwnedPokemon() {
    }

    public OwnedPokemon(Member member, Species species, String gender) {
        setTrainer(member);
        setSpecies(species);
        setGender(gender);
        setJob(false);
        setBox(false);
        setUft(false);
        setRental(false);
    }

    public OwnedPokemon(OwnedPokemonInputDto input, Member member, Species species) {
        update(input, member);
        setSpecies(species);
        if (job == null) setJob(false);
        if (box == null) setBox(false);
        if (uft == null) setUft(false);
        if (rental == null) setRental(false);
    }

    public void update(OwnedPokemonInputDto input, Member member) {
        setTrainer(member);
        setGender(input.getGender());
        setExp(input.getExp());
        setObtainedLink(input.getObtainedLink());
        setNickname(input.getNickname());
        setHiddenPowerLink(input.getHiddenPowerLink());
        setJob(input.getJob());
        setBox(input.getBox());
        setUft(input.getUft());
        setRental(input.getRental());
    }
}
