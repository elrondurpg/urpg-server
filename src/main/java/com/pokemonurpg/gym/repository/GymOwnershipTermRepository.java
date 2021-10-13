package com.pokemonurpg.gym.repository;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymOwnershipTerm;
import com.pokemonurpg.member.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface GymOwnershipTermRepository extends JpaRepository<GymOwnershipTerm, Integer> {
    GymOwnershipTerm findByDbid(Integer dbid);
    GymOwnershipTerm findByGymAndOwnerAndOpenDate(Gym gym, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}