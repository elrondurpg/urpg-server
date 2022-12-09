package com.pokemonurpg.configuration.v2.attack.attack.shared.view;

import com.pokemonurpg.configuration.v2.shared.view.NamedResponse;

public interface AttackResponseContestMoveTypePart extends NamedResponse {
    String getDescription();
    Integer getScore();
    Integer getJam();
}
