package com.pokemonurpg.gym.service;

import com.pokemonurpg.core.service.IndexedObjectService;
import com.pokemonurpg.core.service.IndexedObjectServiceFactory;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.gym.repository.GymRepository;
import com.pokemonurpg.item.service.ItemService;
import com.pokemonurpg.member.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GymService implements IndexedObjectService<Gym>, NamedObjectService<Gym> {

    @Resource
    private GymRepository gymRepository;

    @Resource
    private MemberService memberService;

    @Resource
    private GymLeagueService gymLeagueService;

    @Resource
    private BadgeService badgeService;

    @Resource
    private ItemService itemService;

    @Resource
    private GymPokemonService gymPokemonService;

    public List<String> findAllNames() {
        return gymRepository.findAllNames();
    }

    public Gym findByDbid(Integer dbid) {
        return gymRepository.findByDbid(dbid);
    }

    public Gym findByName(String name) {
        Gym gym = gymRepository.findByName(name);
        if (gym == null && name != null) {
            return gymRepository.findFirstByNameStartingWith(name);
        }
        else return gym;
    }

    public Gym create(GymInputDto input) {
        Gym gym = new Gym(input);
        updateEmbeddedValues(input, gym);
        gymRepository.save(gym);
        updateAssociatedValues(input, gym);
        return gym;
    }

    public Gym update(GymInputDto input, int dbid) {
        Gym gym = gymRepository.findByDbid(dbid);
        if (gym != null) {
            gym.update(input);
            updateEmbeddedValues(input, gym);
            gymRepository.save(gym);
            updateAssociatedValues(input, gym);
        }
        return gym;
    }

    void updateEmbeddedValues(GymInputDto input, Gym gym) {
        gym.setOwner(memberService.findByName(input.getOwner()));
        gym.setLeague(gymLeagueService.findByName(input.getLeague()));
        gym.setBadge(badgeService.findByName(input.getBadge()));
        gym.setTm(itemService.findByName(input.getTm()));
    }

    void updateAssociatedValues(GymInputDto input, Gym gym) {
        gymPokemonService.updateAll(input, gym);
    }
}
