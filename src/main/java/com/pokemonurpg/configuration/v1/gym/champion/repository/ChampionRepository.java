package com.pokemonurpg.configuration.v1.gym.champion.repository;

import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.gym.champion.model.Champion;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;

public interface ChampionRepository extends NamedConfigurationRepository<Champion> {
    Champion findByCurrentOwnerRecord(ChampionOwnershipTerm currentOwnerRecord);
}
