package com.pokemonurpg.infrastructure.data;

import com.pokemonurpg.entities.Champion;
import com.pokemonurpg.entities.ChampionOwnershipTerm;
import com.pokemonurpg.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ChampionOwnershipTermRepository extends JpaRepository<ChampionOwnershipTerm, Integer> {
    ChampionOwnershipTerm findByDbid(Integer dbid);
    ChampionOwnershipTerm findBySlotAndOwnerAndOpenDate(Champion slot, Member owner, Date openDate);
    void deleteByDbid(int dbid);
}