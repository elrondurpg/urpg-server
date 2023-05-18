package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.LogRecord;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRecordRepository extends JpaRepository<LogRecord, Integer> {
    List<LogRecord> findByMember(Member member);
    List<LogRecord> findByCreator(Member creator);
}
