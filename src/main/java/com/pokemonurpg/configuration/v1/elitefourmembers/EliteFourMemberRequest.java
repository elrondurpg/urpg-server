package com.pokemonurpg.configuration.v1.elitefourmembers;

import com.pokemonurpg.entities.v1.EliteFourMember;
import com.pokemonurpg.lib.v1.annotations.UniqueName;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedRequest;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = EliteFourMember.class)
public class EliteFourMemberRequest implements UniquelyNamedRequest {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    public EliteFourMemberRequest() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
