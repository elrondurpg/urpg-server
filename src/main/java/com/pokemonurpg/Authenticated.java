package com.pokemonurpg;

public class Authenticated<T> {
    private T payload;

    private String id;
    private String username;
    private String loginString;
    private String browser;

    public Authenticated() {
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginString() {
        return loginString;
    }

    public void setLoginString(String loginString) {
        this.loginString = loginString;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
