package com.pokemonurpg.configuration.v1.championrecords;

import com.pokemonurpg.configuration.v1.champions.ChampionService;
import com.pokemonurpg.configuration.v1.championslots.ChampionSlotService;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionRecordRepository;
import com.pokemonurpg.lib.v1.services.IndexedObjectService;
import com.pokemonurpg.entities.v1.ChampionSlot;
import com.pokemonurpg.entities.v1.ChampionRecord;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ChampionRecordService implements IndexedObjectService<ChampionRecord> {

    @Resource
    private ChampionRecordRepository championRecordRepository;

    @Resource
    private ChampionSlotService championSlotService;

    @Resource
    private ItemService itemService;

    @Resource
    private MemberService memberService;

    @Resource
    private ChampionService championService;

    public List<ChampionRecord> findAll() {
        return championRecordRepository.findAll();
    }

    public ChampionRecord findByDbid(Integer dbid) {
        return championRecordRepository.findByDbid(dbid);
    }

    public ChampionRecord findBySlotAndOwnerAndOpenDate(String championName, String ownerName, Date openDate) {
        ChampionSlot championSlot = championSlotService.findByName(championName);
        Member owner = memberService.findByNameExact(ownerName);
        return championRecordRepository.findBySlotAndOwnerAndOpenDate(championSlot, owner, openDate);
    }

    public ChampionRecord create(ChampionRecordRequest input) {
        ChampionRecord term = new ChampionRecord(input);

        term.setSlot(championSlotService.findByName(input.getSlot()));
        term.setOwner(memberService.findByNameExact(input.getOwner()));
        championService.create(input.getOwner());

        championRecordRepository.save(term);

        if (input.getBecomeCurrentOwner()) {
            championSlotService.updateCurrentOwnerRecord(term.getSlot(), term);
        }

        return term;
    }

    public ChampionRecord update(ChampionRecordRequest input, int dbid) {
        ChampionRecord term = championRecordRepository.findByDbid(dbid);
        if (term != null) {
            term.update(input);
            championRecordRepository.save(term);

            if (input.getBecomeCurrentOwner()) {
                championSlotService.updateCurrentOwnerRecord(term.getSlot(), term);
            }
        }
        return term;
    }

    public void delete(int dbid) {
        ChampionRecord term = championRecordRepository.findByDbid(dbid);

        if (term != null) {
            ChampionSlot championSlot = championSlotService.findByCurrentOwnerRecord(term);
            if (championSlot != null) {
                championSlot.setCurrentOwnerRecord(null);
            }
            championRecordRepository.deleteByDbid(dbid);
        }
    }
}
