package com.pokemonurpg.entities.v1.stats;

import java.util.List;

import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.shared.IndexedRepository;

public interface LogRecordRepository extends IndexedRepository<LogRecord> {
    List<LogRecord> findByMember(Member member);
    List<LogRecord> findByCreator(Member creator);
}
