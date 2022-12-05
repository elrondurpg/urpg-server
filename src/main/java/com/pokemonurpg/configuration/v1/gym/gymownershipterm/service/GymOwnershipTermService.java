package com.pokemonurpg.configuration.v1.gym.gymownershipterm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.gym.Gym;
import com.pokemonurpg.configuration.v1.gym.gym.service.GymService;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.input.GymOwnershipTermInputDto;
import com.pokemonurpg.entities.v1.gym.GymOwnershipTerm;
import com.pokemonurpg.entities.v1.gym.GymOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.service.KnownGymLeaderService;
import com.pokemonurpg.configuration.v1.gym.league.service.LeagueService;
import com.pokemonurpg.configuration.v1.item.service.ItemService;
import com.pokemonurpg.configuration.v1.lib.service.IndexedConfigurationService;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;

@Service
public class GymOwnershipTermService extends IndexedConfigurationService<GymOwnershipTerm, GymOwnershipTermInputDto> {

    private GymOwnershipTermRepository repository;
    private GymService gymService;
    private LeagueService gymLeagueService;
    private ItemService itemService;
    private MemberService memberService;
    private KnownGymLeaderService knownGymLeaderService;
   
    @Autowired
    public GymOwnershipTermService(
        GymOwnershipTermRepository repository,
        GymService gymService, 
        LeagueService gymLeagueService,
        ItemService itemService,
        MemberService memberService,
        KnownGymLeaderService knownGymLeaderService) {
        super(repository, GymOwnershipTerm.class);
        this.repository = repository;
        this.gymService = gymService;
        this.gymLeagueService = gymLeagueService;
        this.itemService = itemService;
        this.memberService = memberService;
        this.knownGymLeaderService = knownGymLeaderService;
    }

    public GymOwnershipTerm findByGymAndOwnerAndOpenDate(String gymName, String ownerName, Date openDate) {
        Gym gym = gymService.findByName(gymName);
        Member owner = memberService.findByNameExact(ownerName);
        return repository.findByGymAndOwnerAndOpenDate(gym, owner, openDate);
    }

    @Override
    protected void setKeyValues(GymOwnershipTerm model, GymOwnershipTermInputDto input) {
        model.setGym(gymService.findByName(input.getGym()));
        model.setOwner(memberService.findByNameExact(input.getOwner()));
        model.setOpenDate(input.getOpenDate());
    }

    @Override
    protected void updateBase(GymOwnershipTerm model, GymOwnershipTermInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getDraws(), model::setDraws);
        setIfNotNull(input.getLosses(), model::setLosses);
        setIfNotNull(input.getWins(), model::setWins);
    }

    @Override
    protected void updateEmbeddedValues(GymOwnershipTerm model, GymOwnershipTermInputDto input) {
        model.setLeague(gymLeagueService.findByName(input.getLeague()));
        model.setTm(itemService.findByName(input.getTm()));
    }

    @Override
    protected void updateAssociatedValues(GymOwnershipTerm model, GymOwnershipTermInputDto input) {
        knownGymLeaderService.createForNameIfUnique(input.getOwner());

        if (input.getBecomeCurrentOwner()) {
            gymService.updateCurrentOwnerRecord(model.getGym(), model);
        }
    }

    @Override
    protected void deleteAssociatedValues(GymOwnershipTerm model) {
        Gym gym = gymService.findByCurrentOwnerRecord(model);
        if (gym != null) {
            gym.setCurrentOwnerRecord(null);
        }
    }
}
