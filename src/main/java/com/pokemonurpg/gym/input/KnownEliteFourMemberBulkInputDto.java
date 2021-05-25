package com.pokemonurpg.gym.input;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class KnownEliteFourMemberBulkInputDto {
    List<@Valid KnownEliteFourMemberInputDto> eliteFourMembers = new ArrayList<>();

    public KnownEliteFourMemberBulkInputDto() {

    }

    public List<KnownEliteFourMemberInputDto> getEliteFourMembers() {
        return eliteFourMembers;
    }

    public void setEliteFourMembers(List<KnownEliteFourMemberInputDto> eliteFourMembers) {
        this.eliteFourMembers = eliteFourMembers;
    }
}
