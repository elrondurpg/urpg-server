package com.pokemonurpg.configuration.v1.gym.championownershipterm.service;

import com.pokemonurpg.configuration.v1.lib.service.IndexedConfigurationService;
import com.pokemonurpg.configuration.v1.gym.champion.model.Champion;
import com.pokemonurpg.configuration.v1.gym.champion.service.ChampionService;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.input.ChampionOwnershipTermInputDto;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.repository.ChampionOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.gym.knownchampion.service.KnownChampionService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionOwnershipTermService extends IndexedConfigurationService<ChampionOwnershipTerm, ChampionOwnershipTermInputDto> {

    private ChampionOwnershipTermRepository repository;
    private ChampionService championService;
    private MemberService memberService;
    private KnownChampionService knownChampionService;

    @Autowired
    public ChampionOwnershipTermService(
            ChampionOwnershipTermRepository repository,
            ChampionService championService, MemberService memberService,
            KnownChampionService knownChampionService) {
        super(repository, ChampionOwnershipTerm.class);
        this.repository = repository;
        this.championService = championService;
        this.memberService = memberService;
        this.knownChampionService = knownChampionService;
    }

    public ChampionOwnershipTerm findBySlotAndOwnerAndOpenDate(String slotName, String ownerName, Date openDate) {
        Champion slot = championService.findByName(slotName);
        Member owner = memberService.findByNameExact(ownerName);
        return repository.findBySlotAndOwnerAndOpenDate(slot, owner, openDate);
    }

    @Override
    protected void setKeyValues(ChampionOwnershipTerm model, ChampionOwnershipTermInputDto input) {
        model.setSlot(championService.findByName(input.getSlot()));
        model.setOwner(memberService.findByNameExact(input.getOwner()));
        model.setOpenDate(input.getOpenDate());
    }

    @Override
    protected void updateBase(ChampionOwnershipTerm model, ChampionOwnershipTermInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getDraws(), model::setDraws);
        setIfNotNull(input.getLosses(), model::setLosses);
        setIfNotNull(input.getWins(), model::setWins);
    }

    @Override
    protected void updateEmbeddedValues(ChampionOwnershipTerm model, ChampionOwnershipTermInputDto input) {    }

    @Override
    protected void updateAssociatedValues(ChampionOwnershipTerm model, ChampionOwnershipTermInputDto input) {
        knownChampionService.createForNameIfUnique(input.getOwner());

        if (input.getBecomeCurrentOwner()) {
            championService.updateCurrentOwnerRecord(model.getSlot(), model);
        }
    }

    @Override
    protected void deleteAssociatedValues(ChampionOwnershipTerm model) {
        Champion slot = championService.findByCurrentOwnerRecord(model);
        if (slot != null) {
            slot.setCurrentOwnerRecord(null);
        }
    }
}
