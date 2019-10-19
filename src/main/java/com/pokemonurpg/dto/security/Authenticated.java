package com.pokemonurpg.dto.security;

public class Authenticated<T> {
    private T payload;

    private SessionDto session;

    public Authenticated() {
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public SessionDto getSession() {
        return session;
    }

    public void setSession(SessionDto session) {
        this.session = session;
    }
}
