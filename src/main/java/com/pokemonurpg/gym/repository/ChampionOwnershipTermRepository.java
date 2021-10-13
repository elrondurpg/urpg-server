package com.pokemonurpg.gym.repository;

import com.pokemonurpg.gym.models.Champion;
import com.pokemonurpg.gym.models.ChampionOwnershipTerm;
import com.pokemonurpg.member.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ChampionOwnershipTermRepository extends JpaRepository<ChampionOwnershipTerm, Integer> {
    ChampionOwnershipTerm findByDbid(Integer dbid);
    ChampionOwnershipTerm findBySlotAndOwnerAndOpenDate(Champion slot, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}