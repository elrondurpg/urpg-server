package com.pokemonurpg.configuration.v1.contest.oras.input;

import com.pokemonurpg.configuration.v1.contest.ContestMoveTypeInputDto;
import com.pokemonurpg.configuration.v1.contest.oras.model.OrasContestMoveType;
import com.pokemonurpg.core.validation.annotation.UniqueName;

@UniqueName(type = OrasContestMoveType.class)
public class OrasContestMoveTypeInputDto extends ContestMoveTypeInputDto {
    
}
