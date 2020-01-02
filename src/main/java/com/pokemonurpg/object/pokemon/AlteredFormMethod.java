package com.pokemonurpg.object.pokemon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "altered_form")
public class AlteredFormMethod {

    @Id
    @Column(name = "base_dex")
    Integer dexno;

    String method;

    public AlteredFormMethod() {}

    public AlteredFormMethod(Integer dexno, String method) {
        this.dexno = dexno;
        this.method = method;
    }

    public Integer getDexno() {
        return dexno;
    }

    public void setDexno(Integer dexno) {
        this.dexno = dexno;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
