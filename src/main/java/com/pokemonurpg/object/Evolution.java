package com.pokemonurpg.object;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Evolution {

    @EmbeddedId
    EvolutionKey id;

    @Column(name = "evolution_method")
    String method;

    public Evolution() {
    }

    public EvolutionKey getId() {
        return id;
    }

    public void setId(EvolutionKey id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evolution evolution = (Evolution) o;
        return id.equals(evolution.id) &&
                method.equals(evolution.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, method);
    }
}
