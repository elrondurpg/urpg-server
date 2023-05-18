package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.EliteFourMemberSlot;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EliteFourMemberRecordRepository extends JpaRepository<EliteFourMemberRecord, Integer> {
    EliteFourMemberRecord findByDbid(Integer dbid);
    EliteFourMemberRecord findBySlotAndOwnerAndOpenDate(EliteFourMemberSlot slot, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}