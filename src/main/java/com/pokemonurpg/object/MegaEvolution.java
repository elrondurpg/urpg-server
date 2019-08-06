package com.pokemonurpg.object;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "mega_evolution")
@Entity
public class MegaEvolution {

    @EmbeddedId
    MegaEvolutionKey id;

    @Column(name = "mega_stone")
    String megaStone;

    public MegaEvolution() {
    }

    public MegaEvolutionKey getId() {
        return id;
    }

    public void setId(MegaEvolutionKey id) {
        this.id = id;
    }

    public String getMegaStone() {
        return megaStone;
    }

    public void setMegaStone(String megaStone) {
        this.megaStone = megaStone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MegaEvolution megaEvolution = (MegaEvolution) o;
        return id.equals(megaEvolution.id) &&
                megaStone.equals(megaEvolution.megaStone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, megaStone);
    }
}
