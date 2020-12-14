package com.pokemonurpg.stats.service;

import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.stats.models.LogRecord;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.repository.LogRecordRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
