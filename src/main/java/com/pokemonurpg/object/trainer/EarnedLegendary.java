package com.pokemonurpg.object.trainer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "earned_legendary")
public class EarnedLegendary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int dbid;

    @ManyToOne
    @JoinColumn(name = "owned_pokemon_dbid", nullable=false)
    private OwnedPokemon pokemon;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", nullable=false)
    private Member trainer;

    @ManyToOne
    @JoinColumn(name="section_dbid", nullable=false)
    private Section section;

    @Column
    private Date date;

    public EarnedLegendary() {
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public OwnedPokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(OwnedPokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Member getTrainer() {
        return trainer;
    }

    public void setTrainer(Member trainer) {
        this.trainer = trainer;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
