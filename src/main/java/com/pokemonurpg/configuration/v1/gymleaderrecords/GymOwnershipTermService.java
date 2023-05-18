package com.pokemonurpg.configuration.v1.gymleaderrecords;

import com.pokemonurpg.configuration.v1.gymleaders.KnownGymLeaderService;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.lib.v1.services.IndexedObjectService;
import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymOwnershipTerm;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GymOwnershipTermService implements IndexedObjectService<GymOwnershipTerm> {

    @Resource
    private GymOwnershipTermRepository gymOwnershipTermRepository;

    @Resource
    private GymService gymService;

    @Resource
    private GymLeagueService gymLeagueService;

    @Resource
    private ItemService itemService;

    @Resource
    private MemberService memberService;

    @Resource
    private KnownGymLeaderService knownGymLeaderService;

    public List<GymOwnershipTerm> findAll() {
        return gymOwnershipTermRepository.findAll();
    }

    public GymOwnershipTerm findByDbid(Integer dbid) {
        return gymOwnershipTermRepository.findByDbid(dbid);
    }

    public GymOwnershipTerm findByGymAndOwnerAndOpenDate(String gymName, String ownerName, Date openDate) {
        Gym gym = gymService.findByName(gymName);
        Member owner = memberService.findByNameExact(ownerName);
        return gymOwnershipTermRepository.findByGymAndOwnerAndOpenDate(gym, owner, openDate);
    }

    public GymOwnershipTerm create(GymOwnershipTermInputDto input) {
        GymOwnershipTerm term = new GymOwnershipTerm(input);

        term.setGym(gymService.findByName(input.getGym()));
        term.setOwner(memberService.findByNameExact(input.getOwner()));
        knownGymLeaderService.create(input.getOwner());

        updateEmbeddedValues(input, term);

        gymOwnershipTermRepository.save(term);

        if (input.getBecomeCurrentOwner()) {
            gymService.updateCurrentOwnerRecord(term.getGym(), term);
        }

        return term;
    }

    public GymOwnershipTerm update(GymOwnershipTermInputDto input, int dbid) {
        GymOwnershipTerm term = gymOwnershipTermRepository.findByDbid(dbid);
        if (term != null) {
            term.update(input);
            updateEmbeddedValues(input, term);
            gymOwnershipTermRepository.save(term);

            if (input.getBecomeCurrentOwner()) {
                gymService.updateCurrentOwnerRecord(term.getGym(), term);
            }
        }
        return term;
    }

    public void updateEmbeddedValues(GymOwnershipTermInputDto input, GymOwnershipTerm term) {
        term.setLeague(gymLeagueService.findByName(input.getLeague()));
        term.setTm(itemService.findByName(input.getTm()));
    }

    public void delete(int dbid) {
        GymOwnershipTerm term = gymOwnershipTermRepository.findByDbid(dbid);

        if (term != null) {
            Gym gym = gymService.findByCurrentOwnerRecord(term);
            if (gym != null) {
                gym.setCurrentOwnerRecord(null);
            }
            gymOwnershipTermRepository.deleteByDbid(dbid);
        }
    }
}
