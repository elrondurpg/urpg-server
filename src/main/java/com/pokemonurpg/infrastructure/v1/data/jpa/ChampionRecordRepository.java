package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.ChampionSlot;
import com.pokemonurpg.entities.v1.ChampionRecord;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ChampionRecordRepository extends JpaRepository<ChampionRecord, Integer> {
    ChampionRecord findByDbid(Integer dbid);
    ChampionRecord findBySlotAndOwnerAndOpenDate(ChampionSlot slot, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}