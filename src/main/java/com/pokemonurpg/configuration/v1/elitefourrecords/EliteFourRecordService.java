package com.pokemonurpg.configuration.v1.elitefourrecords;

import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourMemberSlotService;
import com.pokemonurpg.configuration.v1.elitefourmembers.EliteFourMemberService;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourMemberRecordRepository;
import com.pokemonurpg.lib.v1.services.IndexedObjectService;
import com.pokemonurpg.entities.v1.EliteFourMemberSlot;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class EliteFourRecordService implements IndexedObjectService<EliteFourMemberRecord> {

    @Resource
    private EliteFourMemberRecordRepository eliteFourMemberRecordRepository;

    @Resource
    private EliteFourMemberSlotService eliteFourMemberSlotService;

    @Resource
    private ItemService itemService;

    @Resource
    private MemberService memberService;

    @Resource
    private EliteFourMemberService eliteFourMemberService;

    public List<EliteFourMemberRecord> findAll() {
        return eliteFourMemberRecordRepository.findAll();
    }

    public EliteFourMemberRecord findByDbid(Integer dbid) {
        return eliteFourMemberRecordRepository.findByDbid(dbid);
    }

    public EliteFourMemberRecord findBySlotAndOwnerAndOpenDate(String eliteFourName, String ownerName, Date openDate) {
        EliteFourMemberSlot eliteFourMemberSlot = eliteFourMemberSlotService.findByName(eliteFourName);
        Member owner = memberService.findByNameExact(ownerName);
        return eliteFourMemberRecordRepository.findBySlotAndOwnerAndOpenDate(eliteFourMemberSlot, owner, openDate);
    }

    public EliteFourMemberRecord create(EliteFourRecordRequest input) {
        EliteFourMemberRecord term = new EliteFourMemberRecord(input);

        term.setSlot(eliteFourMemberSlotService.findByName(input.getSlot()));
        term.setOwner(memberService.findByNameExact(input.getOwner()));
        eliteFourMemberService.create(input.getOwner());

        eliteFourMemberRecordRepository.save(term);

        if (input.getBecomeCurrentOwner()) {
            eliteFourMemberSlotService.updateCurrentOwnerRecord(term.getSlot(), term);
        }

        return term;
    }

    public EliteFourMemberRecord update(EliteFourRecordRequest input, int dbid) {
        EliteFourMemberRecord term = eliteFourMemberRecordRepository.findByDbid(dbid);
        if (term != null) {
            term.update(input);
            eliteFourMemberRecordRepository.save(term);

            if (input.getBecomeCurrentOwner()) {
                eliteFourMemberSlotService.updateCurrentOwnerRecord(term.getSlot(), term);
            }
        }
        return term;
    }

    public void delete(int dbid) {
        EliteFourMemberRecord term = eliteFourMemberRecordRepository.findByDbid(dbid);

        if (term != null) {
            EliteFourMemberSlot eliteFourMemberSlot = eliteFourMemberSlotService.findByCurrentOwnerRecord(term);
            if (eliteFourMemberSlot != null) {
                eliteFourMemberSlot.setCurrentOwnerRecord(null);
            }
            eliteFourMemberRecordRepository.deleteByDbid(dbid);
        }
    }
}
