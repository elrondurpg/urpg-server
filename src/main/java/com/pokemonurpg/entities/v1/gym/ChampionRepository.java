package com.pokemonurpg.entities.v1.gym;

import com.pokemonurpg.entities.v1.shared.NamedRepository;

public interface ChampionRepository extends NamedRepository<Champion> {
    Champion findByCurrentOwnerRecord(ChampionOwnershipTerm currentOwnerRecord);
}
