package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.LogRecord;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.infrastructure.v1.data.jpa.LogRecordRepository;
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
