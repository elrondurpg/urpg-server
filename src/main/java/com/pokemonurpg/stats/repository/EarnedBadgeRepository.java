package com.pokemonurpg.stats.repository;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.EarnedBadge;
import com.pokemonurpg.stats.models.EarnedBadgeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarnedBadgeRepository extends JpaRepository<EarnedBadge, EarnedBadgeKey> {
    EarnedBadge findByMemberAndGym(Member member, Gym gym);
}
