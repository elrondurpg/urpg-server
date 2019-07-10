package com.pokemonurpg.dto;

public class ResponseDto {
    int status;
    String message;

    public ResponseDto() {

    }

    public void notFound() {
        status = 404;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
