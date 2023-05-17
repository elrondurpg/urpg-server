package com.pokemonurpg.stats.repository;

import com.pokemonurpg.stats.models.LogRecord;
import com.pokemonurpg.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRecordRepository extends JpaRepository<LogRecord, Integer> {
    List<LogRecord> findByMember(Member member);
    List<LogRecord> findByCreator(Member creator);
}
