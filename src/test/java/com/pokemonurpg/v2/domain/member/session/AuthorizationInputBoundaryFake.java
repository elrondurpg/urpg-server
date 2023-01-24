package com.pokemonurpg.v2.domain.member.session;

import com.pokemonurpg.v2.lib.enums.ActionEnum;
import com.pokemonurpg.v2.lib.enums.ResourceEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationInputBoundaryFake implements AuthorizationInputBoundary {
    private boolean authorized = true;
    private boolean checked = false;

    @Override
    public boolean authorize(ActionEnum action, ResourceEnum resource) {
        checked = true;
        return action != null && resource != null && authorized;
    }
}
