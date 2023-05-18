package com.pokemonurpg.configuration.v1.championrecords;

import com.pokemonurpg.configuration.v1.championslots.ChampionService;
import com.pokemonurpg.configuration.v1.champions.KnownChampionService;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionOwnershipTermRepository;
import com.pokemonurpg.lib.v1.services.IndexedObjectService;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.ChampionOwnershipTerm;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ChampionOwnershipTermService implements IndexedObjectService<ChampionOwnershipTerm> {

    @Resource
    private ChampionOwnershipTermRepository championOwnershipTermRepository;

    @Resource
    private ChampionService championService;

    @Resource
    private ItemService itemService;

    @Resource
    private MemberService memberService;

    @Resource
    private KnownChampionService knownChampionService;

    public List<ChampionOwnershipTerm> findAll() {
        return championOwnershipTermRepository.findAll();
    }

    public ChampionOwnershipTerm findByDbid(Integer dbid) {
        return championOwnershipTermRepository.findByDbid(dbid);
    }

    public ChampionOwnershipTerm findBySlotAndOwnerAndOpenDate(String championName, String ownerName, Date openDate) {
        Champion champion = championService.findByName(championName);
        Member owner = memberService.findByNameExact(ownerName);
        return championOwnershipTermRepository.findBySlotAndOwnerAndOpenDate(champion, owner, openDate);
    }

    public ChampionOwnershipTerm create(ChampionOwnershipTermInputDto input) {
        ChampionOwnershipTerm term = new ChampionOwnershipTerm(input);

        term.setSlot(championService.findByName(input.getSlot()));
        term.setOwner(memberService.findByNameExact(input.getOwner()));
        knownChampionService.create(input.getOwner());

        championOwnershipTermRepository.save(term);

        if (input.getBecomeCurrentOwner()) {
            championService.updateCurrentOwnerRecord(term.getSlot(), term);
        }

        return term;
    }

    public ChampionOwnershipTerm update(ChampionOwnershipTermInputDto input, int dbid) {
        ChampionOwnershipTerm term = championOwnershipTermRepository.findByDbid(dbid);
        if (term != null) {
            term.update(input);
            championOwnershipTermRepository.save(term);

            if (input.getBecomeCurrentOwner()) {
                championService.updateCurrentOwnerRecord(term.getSlot(), term);
            }
        }
        return term;
    }

    public void delete(int dbid) {
        ChampionOwnershipTerm term = championOwnershipTermRepository.findByDbid(dbid);

        if (term != null) {
            Champion champion = championService.findByCurrentOwnerRecord(term);
            if (champion != null) {
                champion.setCurrentOwnerRecord(null);
            }
            championOwnershipTermRepository.deleteByDbid(dbid);
        }
    }
}
