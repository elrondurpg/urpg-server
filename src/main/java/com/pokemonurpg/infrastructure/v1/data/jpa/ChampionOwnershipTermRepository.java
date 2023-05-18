package com.pokemonurpg.infrastructure.v1.data.jpa;

import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.ChampionOwnershipTerm;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ChampionOwnershipTermRepository extends JpaRepository<ChampionOwnershipTerm, Integer> {
    ChampionOwnershipTerm findByDbid(Integer dbid);
    ChampionOwnershipTerm findBySlotAndOwnerAndOpenDate(Champion slot, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}