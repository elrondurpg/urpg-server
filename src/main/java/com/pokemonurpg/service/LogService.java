package com.pokemonurpg.service;

import com.pokemonurpg.dto.stats.response.LogRecordDto;
import com.pokemonurpg.object.LogRecord;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.repository.LogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LogService {

    public LogRecordRepository logRecordRepository;

    @Autowired
    public LogService(LogRecordRepository logRecordRepository) {
        this.logRecordRepository = logRecordRepository;
    }

    public void log(Member member, String message) {
        if (message.length() > 256) {
            int x = 0;
            int y = 253;
            while (y < message.length()) {
                String chunk = message.substring(x, y) + "...";
                LogRecord log = new LogRecord(member, chunk);
                logRecordRepository.save(log);
                x += 253;
                y += 253;
            }
            String chunk = message.substring(x);
            LogRecord log = new LogRecord(member, chunk);
            logRecordRepository.save(log);
        }
        else {
            LogRecord log = new LogRecord(member, message);
            logRecordRepository.save(log);
        }
    }

    public void log(LogRecord log) {
        logRecordRepository.save(log);
    }

    public List<LogRecordDto> findLogsSinceDateForMember(Date date, Member member) {
        List<LogRecordDto> dtos = new ArrayList<>();
        List<LogRecord> logs = logRecordRepository.findLogsSinceDateForMember(date, member);
        for (LogRecord log : logs) {
            LogRecordDto dto = new LogRecordDto(log);
            dtos.add(dto);
        }
        return dtos;
    }
}
