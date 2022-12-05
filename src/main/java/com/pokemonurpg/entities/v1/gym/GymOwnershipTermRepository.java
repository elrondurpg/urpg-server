package com.pokemonurpg.entities.v1.gym;

import java.util.Date;

import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.shared.IndexedRepository;

public interface GymOwnershipTermRepository extends IndexedRepository<GymOwnershipTerm> {
    GymOwnershipTerm findByGymAndOwnerAndOpenDate(Gym gym, Member owner, Date openDate);
}
