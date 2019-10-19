package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int dbid;

    @Column(name = "discord_id")
    private String discordId;

    @Column
    private String username;

    @Column
    private String password;

    @Column(name = "beta_key")
    private String betaKey;

    @Column
    private Integer salt;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "session_expire")
    private Long sessionExpire;

    public Member() {
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBetaKey() {
        return betaKey;
    }

    public void setBetaKey(String betaKey) {
        this.betaKey = betaKey;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getSessionExpire() {
        return sessionExpire;
    }

    public void setSessionExpire(Long sessionExpire) {
        this.sessionExpire = sessionExpire;
    }
}
