package com.pokemonurpg.gym.service;

import com.pokemonurpg.core.service.IndexedObjectService;
import com.pokemonurpg.gym.input.ChampionOwnershipTermInputDto;
import com.pokemonurpg.gym.models.Champion;
import com.pokemonurpg.gym.models.ChampionOwnershipTerm;
import com.pokemonurpg.gym.repository.ChampionOwnershipTermRepository;
import com.pokemonurpg.item.service.ItemService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
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
