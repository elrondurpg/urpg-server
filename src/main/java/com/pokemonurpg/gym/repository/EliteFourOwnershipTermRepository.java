package com.pokemonurpg.gym.repository;

import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.gym.models.EliteFourOwnershipTerm;
import com.pokemonurpg.member.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EliteFourOwnershipTermRepository extends JpaRepository<EliteFourOwnershipTerm, Integer> {
    EliteFourOwnershipTerm findByDbid(Integer dbid);
    EliteFourOwnershipTerm findBySlotAndOwnerAndOpenDate(EliteFour slot, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}