package com.pokemonurpg.stats.v1;

import com.pokemonurpg.configuration.v1.sections.SectionService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.LegendaryProgress;
import com.pokemonurpg.entities.v1.Section;
import com.pokemonurpg.infrastructure.v1.data.jpa.LegendaryProgressRepository;
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

    public void update(LegendaryProgressRequest input, Member member) {
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
