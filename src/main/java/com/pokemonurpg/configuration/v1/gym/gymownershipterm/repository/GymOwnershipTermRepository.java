package com.pokemonurpg.configuration.v1.gym.gymownershipterm.repository;

import com.pokemonurpg.configuration.v1.lib.repository.IndexedConfigurationRepository;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.member.models.Member;

import java.util.Date;

import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;

public interface GymOwnershipTermRepository extends IndexedConfigurationRepository<GymOwnershipTerm> {
    GymOwnershipTerm findByGymAndOwnerAndOpenDate(Gym gym, Member owner, Date openDate);
}
