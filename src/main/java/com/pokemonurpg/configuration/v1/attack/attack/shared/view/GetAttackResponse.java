package com.pokemonurpg.configuration.v1.attack.attack.shared.view;

import com.pokemonurpg.configuration.v1.shared.view.NamedResponse;

public interface GetAttackResponse extends NamedResponse {
    AttackResponseTypePart getType();
    String getDescription();
    Integer getPower();
    Integer getAccuracy();
    Integer getPp();
    AttackResponseCategoryPart getCategory();
    AttackResponseTargetPart getTarget();
    Boolean getContact();
    Boolean getSnatch();
    Boolean getSubstitute();
    Boolean getSheerForce();
    Boolean getMagicCoat();
    AttackResponseContestMoveTypePart getRseContestMoveType();
    AttackResponseContestMoveTypePart getOrasContestMoveType();
}
