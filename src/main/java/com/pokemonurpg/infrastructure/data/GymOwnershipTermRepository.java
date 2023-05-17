package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymOwnershipTerm;
import com.pokemonurpg.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface GymOwnershipTermRepository extends JpaRepository<GymOwnershipTerm, Integer> {
    GymOwnershipTerm findByDbid(Integer dbid);
    GymOwnershipTerm findByGymAndOwnerAndOpenDate(Gym gym, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}