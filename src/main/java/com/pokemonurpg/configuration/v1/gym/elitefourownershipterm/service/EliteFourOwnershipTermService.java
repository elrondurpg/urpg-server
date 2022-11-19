package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.service;

import com.pokemonurpg.configuration.v1.lib.service.IndexedConfigurationService;
import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.gym.service.EliteFourService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.input.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.repository.EliteFourOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.service.KnownEliteFourMemberService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EliteFourOwnershipTermService extends IndexedConfigurationService<EliteFourOwnershipTerm, EliteFourOwnershipTermInputDto> {

    private EliteFourOwnershipTermRepository repository;
    private EliteFourService eliteFourService;
    private MemberService memberService;
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Autowired
    public EliteFourOwnershipTermService(
            EliteFourOwnershipTermRepository repository,
            EliteFourOwnershipTermRepository eliteFourOwnershipTermRepository,
            EliteFourService eliteFourService, MemberService memberService,
            KnownEliteFourMemberService knownEliteFourMemberService) {
        super(repository, EliteFourOwnershipTerm.class);
        this.repository = eliteFourOwnershipTermRepository;
        this.eliteFourService = eliteFourService;
        this.memberService = memberService;
        this.knownEliteFourMemberService = knownEliteFourMemberService;
    }

    public EliteFourOwnershipTerm findBySlotAndOwnerAndOpenDate(String slotName, String ownerName, Date openDate) {
        EliteFour slot = eliteFourService.findByName(slotName);
        Member owner = memberService.findByNameExact(ownerName);
        return repository.findBySlotAndOwnerAndOpenDate(slot, owner, openDate);
    }

    @Override
    protected void setKeyValues(EliteFourOwnershipTerm model, EliteFourOwnershipTermInputDto input) {
        model.setSlot(eliteFourService.findByName(input.getSlot()));
        model.setOwner(memberService.findByNameExact(input.getOwner()));
        model.setOpenDate(input.getOpenDate());
    }

    @Override
    protected void updateBase(EliteFourOwnershipTerm model, EliteFourOwnershipTermInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getDraws(), model::setDraws);
        setIfNotNull(input.getLosses(), model::setLosses);
        setIfNotNull(input.getWins(), model::setWins);
    }

    @Override
    protected void updateEmbeddedValues(EliteFourOwnershipTerm model, EliteFourOwnershipTermInputDto input) {    }

    @Override
    protected void updateAssociatedValues(EliteFourOwnershipTerm model, EliteFourOwnershipTermInputDto input) {
        knownEliteFourMemberService.createForNameIfUnique(input.getOwner());

        if (input.getBecomeCurrentOwner()) {
            eliteFourService.updateCurrentOwnerRecord(model.getSlot(), model);
        }
    }

    @Override
    protected void deleteAssociatedValues(EliteFourOwnershipTerm model) {
        EliteFour slot = eliteFourService.findByCurrentOwnerRecord(model);
        if (slot != null) {
            slot.setCurrentOwnerRecord(null);
        }
    }
}
