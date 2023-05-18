package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.entities.v1.EliteFourOwnershipTerm;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EliteFourOwnershipTermRepository extends JpaRepository<EliteFourOwnershipTerm, Integer> {
    EliteFourOwnershipTerm findByDbid(Integer dbid);
    EliteFourOwnershipTerm findBySlotAndOwnerAndOpenDate(EliteFour slot, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}