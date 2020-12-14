package com.pokemonurpg.stats.service;

import com.pokemonurpg.core.service.IndexedObjectService;
import com.pokemonurpg.general.service.SectionService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.LegendaryProgressInputDto;
import com.pokemonurpg.stats.models.LegendaryProgress;
import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.stats.repository.LegendaryProgressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

@Service
public class LegendaryProgressService implements IndexedObjectService<LegendaryProgress> {

    @Resource
    private LegendaryProgressRepository legendaryProgressRepository;

    @Resource
    private SectionService sectionService;

    public LegendaryProgress findByDbid(Integer dbid) {
        return legendaryProgressRepository.findByDbid(dbid);
    }

    public void update(LegendaryProgressInputDto input, Member member) {
        LegendaryProgress existingRecord = legendaryProgressRepository.findByDbid(input.getDbid());
        if (existingRecord != null) {
            if (input.getDelete()) {
                legendaryProgressRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                legendaryProgressRepository.save(existingRecord);
            }
        }
        else {
            Section section = sectionService.findByName(input.getSection());
            LegendaryProgress legendaryProgress = new LegendaryProgress(input, member, section);
            legendaryProgressRepository.save(legendaryProgress);
        }
    }
}
