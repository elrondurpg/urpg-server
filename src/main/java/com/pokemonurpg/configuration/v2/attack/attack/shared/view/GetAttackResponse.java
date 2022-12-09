package com.pokemonurpg.configuration.v2.attack.attack.shared.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v2.shared.view.NamedResponse;

public interface GetAttackResponse extends NamedResponse {
    @JsonSerialize(as = AttackResponseTypePart.class)
    AttackResponseTypePart getType();
    String getDescription();
    Integer getPower();
    Integer getAccuracy();
    Integer getPp();
    @JsonSerialize(as = AttackResponseCategoryPart.class)
    AttackResponseCategoryPart getCategory();
    @JsonSerialize(as = AttackResponseTargetPart.class)
    AttackResponseTargetPart getTarget();
    Boolean getContact();
    Boolean getSnatch();
    Boolean getSubstitute();
    Boolean getSheerForce();
    Boolean getMagicCoat();
    @JsonSerialize(as = AttackResponseContestMoveTypePart.class)
    AttackResponseContestMoveTypePart getRseContestMoveType();
    @JsonSerialize(as = AttackResponseContestMoveTypePart.class)
    AttackResponseContestMoveTypePart getOrasContestMoveType();
}
