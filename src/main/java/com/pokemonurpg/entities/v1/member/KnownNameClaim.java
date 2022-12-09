package com.pokemonurpg.entities.v1.member;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class KnownNameClaim extends NamedEntity {

    @Column(name = "discord_id")
    private String discordId;

    public KnownNameClaim() {

    }

    @Builder
    public KnownNameClaim(String discordId, String name) {
        setName(name);
        setDiscordId(discordId);
    }
}
