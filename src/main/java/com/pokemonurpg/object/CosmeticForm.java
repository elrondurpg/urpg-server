package com.pokemonurpg.object;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CosmeticForm {
    @EmbeddedId
    CosmeticFormKey id;

    @Column(name = "display_name")
    private String displayName;

    @Column
    private String method;

    public CosmeticForm() {}

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
                Objects.equals(getDisplayName(), that.getDisplayName()) &&
                Objects.equals(getMethod(), that.getMethod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDisplayName(), getMethod());
    }

}
