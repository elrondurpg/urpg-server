package com.pokemonurpg.stats.repository;

import com.pokemonurpg.stats.models.LogRecord;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LogRecordRepository extends JpaRepository<LogRecord, Integer> {
    List<LogRecord> findByMember(Member member);
    List<LogRecord> findByCreator(Member creator);
}
