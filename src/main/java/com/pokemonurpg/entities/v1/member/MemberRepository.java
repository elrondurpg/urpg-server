package com.pokemonurpg.entities.v1.member;

import com.pokemonurpg.entities.v1.shared.NamedRepository;

public interface MemberRepository extends NamedRepository<Member> {
    Member findByDiscordId(String discordId);
}
