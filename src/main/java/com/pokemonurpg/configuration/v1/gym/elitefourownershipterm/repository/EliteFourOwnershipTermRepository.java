package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.repository;

import com.pokemonurpg.configuration.v1.lib.repository.IndexedConfigurationRepository;
import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.member.models.Member;

import java.util.Date;

import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;

public interface EliteFourOwnershipTermRepository extends IndexedConfigurationRepository<EliteFourOwnershipTerm> {
    EliteFourOwnershipTerm findBySlotAndOwnerAndOpenDate(EliteFour slot, Member owner, Date openDate);
}
