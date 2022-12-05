package com.pokemonurpg.entities.v1.gym;

import java.util.Date;

import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.shared.IndexedRepository;

public interface EliteFourOwnershipTermRepository extends IndexedRepository<EliteFourOwnershipTerm> {
    EliteFourOwnershipTerm findBySlotAndOwnerAndOpenDate(EliteFour slot, Member owner, Date openDate);
}
