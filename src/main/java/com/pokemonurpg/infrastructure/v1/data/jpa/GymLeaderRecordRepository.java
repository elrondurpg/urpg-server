package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeaderRecord;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface GymLeaderRecordRepository extends JpaRepository<GymLeaderRecord, Integer> {
    GymLeaderRecord findByDbid(Integer dbid);
    GymLeaderRecord findByGymAndOwnerAndOpenDate(Gym gym, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}