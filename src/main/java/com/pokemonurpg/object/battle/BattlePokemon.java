package com.pokemonurpg.object.battle;

import com.pokemonurpg.object.pokemon.Ability;
import com.pokemonurpg.object.pokemon.Species;
import com.pokemonurpg.object.pokemon.Type;
import com.pokemonurpg.object.trainer.Item;
import com.pokemonurpg.object.trainer.Member;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "battle_pokemon")
public class BattlePokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int dbid;

    @Column
    private String nickname;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", nullable=false)
    private Member trainer;

    @OneToOne
    @JoinColumn(name = "species_dbid", nullable=false)
    private Species species;

    @OneToOne
    @JoinColumn(name = "battle_dbid", nullable=false)
    private Battle battle;

    @Column
    private char gender;

    @OneToOne
    @JoinColumn(name = "ability_dbid", nullable=false)
    private Ability ability;

    @OneToOne
    @JoinColumn(name = "item_dbid", nullable=false)
    private Item item;

    @Column(name = "sequence_no")
    private int sequenceNo;

    @Column
    private boolean active;

    @Column
    private int position;

    @OneToMany(mappedBy="pokemon")
    private List<BattlePokemonStatus> statuses;

    public BattlePokemon() {
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Member getTrainer() {
        return trainer;
    }

    public void setTrainer(Member trainer) {
        this.trainer = trainer;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<BattlePokemonStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<BattlePokemonStatus> statuses) {
        this.statuses = statuses;
    }
}
