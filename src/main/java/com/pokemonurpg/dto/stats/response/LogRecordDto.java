package com.pokemonurpg.dto.stats.response;

import com.pokemonurpg.object.trainer.LogRecord;

public class LogRecordDto {
    private long timestamp;
    private String message;

    public LogRecordDto() {
    }

    public LogRecordDto(LogRecord log) {
        if (log != null) {
            setTimestamp(log.getTimestamp().getTime());
            setMessage(log.getMessage());
        }
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
