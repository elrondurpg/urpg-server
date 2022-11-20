package com.pokemonurpg.configuration.v1.gym.championownershipterm.repository;

import java.util.Date;

import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.lib.repository.IndexedConfigurationRepository;
import com.pokemonurpg.configuration.v1.gym.champion.model.Champion;
import com.pokemonurpg.member.models.Member;

public interface ChampionOwnershipTermRepository extends IndexedConfigurationRepository<ChampionOwnershipTerm> {
    ChampionOwnershipTerm findBySlotAndOwnerAndOpenDate(Champion slot, Member owner, Date openDate);
}
