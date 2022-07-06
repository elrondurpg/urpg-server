package com.pokemonurpg.gym.service;

import com.pokemonurpg.core.service.IndexedObjectService;
import com.pokemonurpg.gym.input.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.gym.models.EliteFourOwnershipTerm;
import com.pokemonurpg.gym.repository.EliteFourOwnershipTermRepository;
import com.pokemonurpg.item.service.ItemService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
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
