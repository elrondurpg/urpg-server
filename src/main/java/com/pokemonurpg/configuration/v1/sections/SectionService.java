package com.pokemonurpg.configuration.v1.sections;

import com.pokemonurpg.infrastructure.data.SectionRepository;
import com.pokemonurpg.entities.Section;
import com.pokemonurpg.lib.service.NamedObjectService;
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
        Section section = findByNameExact(name);
        if (section == null && name != null) {
            return sectionRepository.findFirstByNameStartingWith(name);
        }
        else return section;
    }

    @Override
    public Section findByNameExact(String name) {
        return sectionRepository.findByName(name);
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
