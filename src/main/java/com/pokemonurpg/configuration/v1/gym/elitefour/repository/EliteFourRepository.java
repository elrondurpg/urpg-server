package com.pokemonurpg.configuration.v1.gym.elitefour.repository;

import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.gym.elitefour.model.EliteFour;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;

public interface EliteFourRepository extends NamedConfigurationRepository<EliteFour> {
    EliteFour findByCurrentOwnerRecord(EliteFourOwnershipTerm currentOwnerRecord);

}
