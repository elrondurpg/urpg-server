package com.pokemonurpg.entities.v1.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class KnownNameClaim extends NamedEntity {

    @Id
    @Column(name = "discord_id")
    private String discordId;

    public KnownNameClaim() {

    }

    public KnownNameClaim(String discordId, String name) {
        setName(name);
        setDiscordId(discordId);
    }
}
