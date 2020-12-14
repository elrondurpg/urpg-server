package com.pokemonurpg.stats.service;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.service.GymService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.EarnedBadgeInputDto;
import com.pokemonurpg.stats.models.EarnedBadge;
import com.pokemonurpg.stats.repository.EarnedBadgeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

@Service
public class EarnedBadgeService {

    @Resource
    private EarnedBadgeRepository earnedBadgeRepository;

    @Resource
    private GymService gymService;

    public void update(Member member, EarnedBadgeInputDto input) {
        Gym gym = gymService.findByDbid(input.getGymDbid());
        if (gym != null) {
            EarnedBadge existingRecord = earnedBadgeRepository.findByMemberAndGym(member, gym);
            if (existingRecord != null) {
                if (input.getDelete()) {
                    earnedBadgeRepository.delete(existingRecord);
                } else {
                    existingRecord.update(input);
                    earnedBadgeRepository.save(existingRecord);
                }
            } else {
                EarnedBadge newRecord = new EarnedBadge(input, member, gym);
                earnedBadgeRepository.save(newRecord);
            }
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid gym specified for earned badge. DBID: " + input.getGymDbid());
    }
}
