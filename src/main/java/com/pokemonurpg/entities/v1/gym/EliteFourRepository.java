package com.pokemonurpg.entities.v1.gym;

import com.pokemonurpg.entities.v1.shared.NamedRepository;

public interface EliteFourRepository extends NamedRepository<EliteFour> {
    EliteFour findByCurrentOwnerRecord(EliteFourOwnershipTerm currentOwnerRecord);

}
