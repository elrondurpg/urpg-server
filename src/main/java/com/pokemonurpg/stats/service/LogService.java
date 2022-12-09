package com.pokemonurpg.stats.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.stats.LogRecord;
import com.pokemonurpg.entities.v1.stats.LogRecordRepository;

@Service
public class LogService {

    @Resource
    private LogRecordRepository logRecordRepository;

    public List<LogRecord> findByMember(Member member) {
        return logRecordRepository.findByMember(member);
    }

    public List<LogRecord> findByCreator(Member creator) {
        return logRecordRepository.findByCreator(creator);
    }

    public void create(Member member, Member creator, String message) {
        while(message.length() > 256) {
            save(new LogRecord(member, creator, message.substring(0, 253) + "..."));
            message = message.substring(253);
        }

        save(new LogRecord(member, creator, message));
    }

    private void save(LogRecord log) {
        logRecordRepository.save(log);
    }
}
