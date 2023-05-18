package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymOwnershipTerm;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface GymOwnershipTermRepository extends JpaRepository<GymOwnershipTerm, Integer> {
    GymOwnershipTerm findByDbid(Integer dbid);
    GymOwnershipTerm findByGymAndOwnerAndOpenDate(Gym gym, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}