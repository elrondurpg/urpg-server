package com.pokemonurpg.dto.species.response;

import com.pokemonurpg.object.Ability;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.SpeciesAbility;

public class SpeciesAbilityDto implements Comparable<SpeciesAbilityDto> {
    private int dbid;
    private String name;
    private String description;
    private boolean hidden;

    public SpeciesAbilityDto () { }

    public SpeciesAbilityDto (SpeciesAbility sa) {
        if (sa != null) {
            Ability ability = sa.getAbility();
            if (ability != null) {
                dbid = ability.getDbid();
                name = ability.getName();
                description = ability.getDescription();
            }
            hidden = sa.getHidden();
        }
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public int compareTo(SpeciesAbilityDto o) {
        return ("" + hidden + name).compareTo("" + o.isHidden() + o.getName());
    }
}
