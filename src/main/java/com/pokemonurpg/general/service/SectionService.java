package com.pokemonurpg.general.service;

import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.general.input.SectionInputDto;
import com.pokemonurpg.general.repository.SectionRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SectionService implements NamedObjectService<Section> {

    @Resource
    private SectionRepository sectionRepository;

    public List<Section> findAll() { return sectionRepository.findAll(); }

    public List<String> findAllNames() {
        return sectionRepository.findAllNames();
    }

    public Section findByDbid(int dbid) {
        return sectionRepository.findByDbid(dbid);
    }

    public Section findByName(String name) {
        Section section = sectionRepository.findByName(name);
        if (section == null && name != null) {
            return sectionRepository.findFirstByNameStartingWith(name);
        }
        else return section;
    }

    public Section create(SectionInputDto input) {
        Section section = new Section(input);
        sectionRepository.save(section);
        return section;
    }

    public Section update(SectionInputDto input, int dbid) {
        Section section = sectionRepository.findByDbid(dbid);
        if (section != null) {
            section.update(input);
            sectionRepository.save(section);
        }
        return section;
    }
}
