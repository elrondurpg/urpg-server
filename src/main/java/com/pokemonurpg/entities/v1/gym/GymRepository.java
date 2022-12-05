package com.pokemonurpg.entities.v1.gym;

import com.pokemonurpg.entities.v1.shared.NamedRepository;

public interface GymRepository extends NamedRepository<Gym> {
    Gym findByCurrentOwnerRecord(GymOwnershipTerm currentOwnerRecord);
}
