package com.pokemonurpg.configuration.v1.gym.gym.repository;

import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;

public interface GymRepository extends NamedConfigurationRepository<Gym> {
    Gym findByCurrentOwnerRecord(GymOwnershipTerm currentOwnerRecord);
}
