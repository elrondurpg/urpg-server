package com.pokemonurpg.general.service;

import com.pokemonurpg.general.models.Obtained;
import com.pokemonurpg.general.input.ObtainedInputDto;
import com.pokemonurpg.general.repository.ObtainedRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ObtainedService implements NamedObjectService<Obtained> {

    @Resource
    private ObtainedRepository obtainedRepository;

    public List<String> findAllNames() {
        return obtainedRepository.findAllNames();
    }

    public Obtained findByDbid(int dbid) {
        return obtainedRepository.findByDbid(dbid);
    }

    public Obtained findByName(String name) {
        Obtained obtained = obtainedRepository.findByName(name);
        if (obtained == null && name != null) {
            return obtainedRepository.findFirstByNameStartingWith(name);
        }
        else return obtained;
    }

    public Obtained create(ObtainedInputDto input) {
        Obtained obtained = new Obtained(input);
        obtainedRepository.save(obtained);
        return obtained;
    }

    public Obtained update(ObtainedInputDto input, int dbid) {
        Obtained obtained = obtainedRepository.findByDbid(dbid);
        if (obtained != null) {
            obtained.update(input);
            obtainedRepository.save(obtained);
        }
        return obtained;
    }
}
