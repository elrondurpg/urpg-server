package com.pokemonurpg.configuration.v1.contest.rse.input;

import com.pokemonurpg.configuration.v1.contest.ContestMoveTypeInputDto;
import com.pokemonurpg.configuration.v1.contest.rse.model.RseContestMoveType;
import com.pokemonurpg.core.validation.annotation.UniqueName;

@UniqueName(type = RseContestMoveType.class)
public class RseContestMoveTypeInputDto extends ContestMoveTypeInputDto {
    
}
