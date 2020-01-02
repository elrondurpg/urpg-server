package com.pokemonurpg.object.pokemon;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CosmeticForm {
    @EmbeddedId
    CosmeticFormKey id;

    @Column(name = "form_name")
    private String formName;

    @Column
    private String method;

    public CosmeticForm() {}

    public CosmeticForm(int speciesDbid, String name, String formName, String method) {
        setId(new CosmeticFormKey(speciesDbid, name));
        this.formName = formName;
        this.method = method;
    }

    public CosmeticFormKey getId() {
        return id;
    }

    public void setId(CosmeticFormKey id) {
        this.id = id;
    }

    public String getName() {
        return id.getName();
    }

    public void setName(String name) {
        this.id.setName(name);
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CosmeticForm)) return false;
        CosmeticForm that = (CosmeticForm) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getFormName(), that.getFormName()) &&
                Objects.equals(getMethod(), that.getMethod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getFormName(), getMethod());
    }

}
