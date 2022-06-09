package com.pokemonurpg.general.service;

import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.general.input.FlagInputDto;
import com.pokemonurpg.general.models.Flag;
import com.pokemonurpg.general.repository.FlagRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FlagService implements NamedObjectService<Flag> {

    @Resource
    private FlagRepository flagRepository;

    public List<String> findAllNames() {
        return flagRepository.findAllNames();
    }

    public Flag findByDbid(int dbid) {
        return flagRepository.findByDbid(dbid);
    }

    public Flag findByName(String name) {
        Flag flag = findByNameExact(name);
        if (flag == null && name != null) {
            return flagRepository.findFirstByNameStartingWith(name);
        }
        else return flag;
    }

    @Override
    public Flag findByNameExact(String name) {
        return flagRepository.findByName(name);
    }

    public Flag create(FlagInputDto input) {
        Flag flag = new Flag(input);
        flagRepository.save(flag);
        return flag;
    }

    public Flag update(FlagInputDto input, int dbid) {
        Flag flag = flagRepository.findByDbid(dbid);
        if (flag != null) {
            flag.update(input);
            flagRepository.save(flag);
        }
        return flag;
    }
}
