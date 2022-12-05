package com.pokemonurpg.configuration.v1.attack.attack.shared.view;

import com.pokemonurpg.configuration.v1.shared.view.NamedResponse;

public interface AttackResponseContestMoveTypePart extends NamedResponse {
    String getDescription();
    Integer getScore();
    Integer getJam();
}
