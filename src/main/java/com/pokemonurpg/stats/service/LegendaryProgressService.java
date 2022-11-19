package com.pokemonurpg.stats.service;

import com.pokemonurpg.core.service.IndexedObjectService;
import com.pokemonurpg.configuration.v1.site.section.service.SectionService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.LegendaryProgressInputDto;
import com.pokemonurpg.stats.models.LegendaryProgress;
import com.pokemonurpg.configuration.v1.site.section.model.Section;
import com.pokemonurpg.stats.repository.LegendaryProgressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class LegendaryProgressService {

    @Resource
    private LegendaryProgressRepository legendaryProgressRepository;

    @Resource
    private SectionService sectionService;

    public List<LegendaryProgress> findByTrainer(Member trainer) {
        return legendaryProgressRepository.findByTrainer(trainer);
    }

    public void update(LegendaryProgressInputDto input, Member member) {
        Section section = sectionService.findByName(input.getSection());
        LegendaryProgress existingRecord = legendaryProgressRepository.findByTrainerAndIdLogUrlAndSection(member, input.getLogUrl(), section);
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
            LegendaryProgress legendaryProgress = new LegendaryProgress(input, member, section);
            legendaryProgressRepository.save(legendaryProgress);
        }
    }
}
