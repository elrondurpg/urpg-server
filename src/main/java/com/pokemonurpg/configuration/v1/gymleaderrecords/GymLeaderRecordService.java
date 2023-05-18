package com.pokemonurpg.configuration.v1.gymleaderrecords;

import com.pokemonurpg.configuration.v1.gymleaders.GymLeaderService;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymLeaderRecordRepository;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.lib.v1.services.IndexedObjectService;
import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeaderRecord;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GymLeaderRecordService implements IndexedObjectService<GymLeaderRecord> {

    @Resource
    private GymLeaderRecordRepository gymLeaderRecordRepository;

    @Resource
    private GymService gymService;

    @Resource
    private GymLeagueService gymLeagueService;

    @Resource
    private ItemService itemService;

    @Resource
    private MemberService memberService;

    @Resource
    private GymLeaderService gymLeaderService;

    public List<GymLeaderRecord> findAll() {
        return gymLeaderRecordRepository.findAll();
    }

    public GymLeaderRecord findByDbid(Integer dbid) {
        return gymLeaderRecordRepository.findByDbid(dbid);
    }

    public GymLeaderRecord findByGymAndOwnerAndOpenDate(String gymName, String ownerName, Date openDate) {
        Gym gym = gymService.findByName(gymName);
        Member owner = memberService.findByNameExact(ownerName);
        return gymLeaderRecordRepository.findByGymAndOwnerAndOpenDate(gym, owner, openDate);
    }

    public GymLeaderRecord create(GymLeaderRecordRequest input) {
        GymLeaderRecord term = new GymLeaderRecord(input);

        term.setGym(gymService.findByName(input.getGym()));
        term.setOwner(memberService.findByNameExact(input.getOwner()));
        gymLeaderService.create(input.getOwner());

        updateEmbeddedValues(input, term);

        gymLeaderRecordRepository.save(term);

        if (input.getBecomeCurrentOwner()) {
            gymService.updateCurrentOwnerRecord(term.getGym(), term);
        }

        return term;
    }

    public GymLeaderRecord update(GymLeaderRecordRequest input, int dbid) {
        GymLeaderRecord term = gymLeaderRecordRepository.findByDbid(dbid);
        if (term != null) {
            term.update(input);
            updateEmbeddedValues(input, term);
            gymLeaderRecordRepository.save(term);

            if (input.getBecomeCurrentOwner()) {
                gymService.updateCurrentOwnerRecord(term.getGym(), term);
            }
        }
        return term;
    }

    public void updateEmbeddedValues(GymLeaderRecordRequest input, GymLeaderRecord term) {
        term.setLeague(gymLeagueService.findByName(input.getLeague()));
        term.setTm(itemService.findByName(input.getTm()));
    }

    public void delete(int dbid) {
        GymLeaderRecord term = gymLeaderRecordRepository.findByDbid(dbid);

        if (term != null) {
            Gym gym = gymService.findByCurrentOwnerRecord(term);
            if (gym != null) {
                gym.setCurrentOwnerRecord(null);
            }
            gymLeaderRecordRepository.deleteByDbid(dbid);
        }
    }
}
