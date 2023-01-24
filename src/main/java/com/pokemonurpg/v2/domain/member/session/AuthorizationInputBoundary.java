package com.pokemonurpg.v2.domain.member.session;

import com.pokemonurpg.v2.lib.enums.ActionEnum;
import com.pokemonurpg.v2.lib.enums.ResourceEnum;

public interface AuthorizationInputBoundary {
    boolean authorize(ActionEnum action, ResourceEnum resource);
}
