package com.pokemonurpg.member.models;

import javax.persistence.*;

@Entity
public class KnownNameClaim {

    @Id
    @Column(name = "discord_id")
    private String discordId;

    @Column
    private String name;

    public KnownNameClaim() {

    }

    public KnownNameClaim(String discordId, String name) {
        setName(name);
        setDiscordId(discordId);
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
