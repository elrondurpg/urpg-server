package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.EliteFour;
import com.pokemonurpg.entities.EliteFourOwnershipTerm;
import com.pokemonurpg.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EliteFourOwnershipTermRepository extends JpaRepository<EliteFourOwnershipTerm, Integer> {
    EliteFourOwnershipTerm findByDbid(Integer dbid);
    EliteFourOwnershipTerm findBySlotAndOwnerAndOpenDate(EliteFour slot, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}