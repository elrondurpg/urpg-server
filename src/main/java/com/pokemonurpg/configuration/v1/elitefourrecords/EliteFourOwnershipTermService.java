package com.pokemonurpg.configuration.v1.elitefourrecords;

import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourService;
import com.pokemonurpg.configuration.v1.elitefourmembers.KnownEliteFourMemberService;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourOwnershipTermRepository;
import com.pokemonurpg.lib.v1.service.IndexedObjectService;
import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.entities.v1.EliteFourOwnershipTerm;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class EliteFourOwnershipTermService implements IndexedObjectService<EliteFourOwnershipTerm> {

    @Resource
    private EliteFourOwnershipTermRepository eliteFourOwnershipTermRepository;

    @Resource
    private EliteFourService eliteFourService;

    @Resource
    private ItemService itemService;

    @Resource
    private MemberService memberService;

    @Resource
    private KnownEliteFourMemberService knownEliteFourMemberService;

    public List<EliteFourOwnershipTerm> findAll() {
        return eliteFourOwnershipTermRepository.findAll();
    }

    public EliteFourOwnershipTerm findByDbid(Integer dbid) {
        return eliteFourOwnershipTermRepository.findByDbid(dbid);
    }

    public EliteFourOwnershipTerm findBySlotAndOwnerAndOpenDate(String eliteFourName, String ownerName, Date openDate) {
        EliteFour eliteFour = eliteFourService.findByName(eliteFourName);
        Member owner = memberService.findByNameExact(ownerName);
        return eliteFourOwnershipTermRepository.findBySlotAndOwnerAndOpenDate(eliteFour, owner, openDate);
    }

    public EliteFourOwnershipTerm create(EliteFourOwnershipTermInputDto input) {
        EliteFourOwnershipTerm term = new EliteFourOwnershipTerm(input);

        term.setSlot(eliteFourService.findByName(input.getSlot()));
        term.setOwner(memberService.findByNameExact(input.getOwner()));
        knownEliteFourMemberService.create(input.getOwner());

        eliteFourOwnershipTermRepository.save(term);

        if (input.getBecomeCurrentOwner()) {
            eliteFourService.updateCurrentOwnerRecord(term.getSlot(), term);
        }

        return term;
    }

    public EliteFourOwnershipTerm update(EliteFourOwnershipTermInputDto input, int dbid) {
        EliteFourOwnershipTerm term = eliteFourOwnershipTermRepository.findByDbid(dbid);
        if (term != null) {
            term.update(input);
            eliteFourOwnershipTermRepository.save(term);

            if (input.getBecomeCurrentOwner()) {
                eliteFourService.updateCurrentOwnerRecord(term.getSlot(), term);
            }
        }
        return term;
    }

    public void delete(int dbid) {
        EliteFourOwnershipTerm term = eliteFourOwnershipTermRepository.findByDbid(dbid);

        if (term != null) {
            EliteFour eliteFour = eliteFourService.findByCurrentOwnerRecord(term);
            if (eliteFour != null) {
                eliteFour.setCurrentOwnerRecord(null);
            }
            eliteFourOwnershipTermRepository.deleteByDbid(dbid);
        }
    }
}
