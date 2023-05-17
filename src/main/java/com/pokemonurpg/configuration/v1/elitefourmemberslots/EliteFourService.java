package com.pokemonurpg.configuration.v1.elitefourmemberslots;

import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.infrastructure.data.EliteFourOwnershipTermRepository;
import com.pokemonurpg.infrastructure.data.EliteFourRepository;
import com.pokemonurpg.lib.service.NamedObjectService;
import com.pokemonurpg.entities.EliteFour;
import com.pokemonurpg.entities.EliteFourOwnershipTerm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EliteFourService implements NamedObjectService<EliteFour> {

    @Resource
    private EliteFourRepository eliteFourRepository;

    @Resource
    private BadgeService badgeService;

    @Resource
    private TypeService typeService;

    @Resource
    private EliteFourOwnershipTermRepository eliteFourOwnershipTermRepository;

    @Resource
    private EliteFourPokemonService eliteFourPokemonService;

    public List<String> findAllNames() {
        return eliteFourRepository.findAllNames();
    }

    public EliteFour findByDbid(Integer dbid) {
        return eliteFourRepository.findByDbid(dbid);
    }

    public EliteFour findByName(String name) {
        EliteFour eliteFour = findByNameExact(name);
        if (eliteFour == null && name != null) {
            return eliteFourRepository.findFirstByNameStartingWith(name);
        }
        else return eliteFour;
    }

    @Override
    public EliteFour findByNameExact(String name) {
        return eliteFourRepository.findByName(name);
    }

    public EliteFour findByCurrentOwnerRecord(EliteFourOwnershipTerm ownerRecord) {
        return eliteFourRepository.findByCurrentOwnerRecord(ownerRecord);
    }


    public EliteFour create(EliteFourInputDto input) {
        EliteFour eliteFour = new EliteFour(input);
        updateEmbeddedValues(input, eliteFour);
        eliteFourRepository.save(eliteFour);
        return eliteFour;
    }

    public EliteFour update(EliteFourInputDto input, int dbid) {
        EliteFour eliteFour = eliteFourRepository.findByDbid(dbid);
        if (eliteFour != null) {
            eliteFour.update(input);
            updateEmbeddedValues(input, eliteFour);
            eliteFourRepository.save(eliteFour);
        }
        return eliteFour;
    }

    public void updateCurrentOwnerRecord(EliteFour eliteFour, EliteFourOwnershipTerm record) {
        eliteFour.setCurrentOwnerRecord(record);
        eliteFourRepository.save(eliteFour);
    }

    void updateEmbeddedValues(EliteFourInputDto input, EliteFour eliteFour) {
        eliteFourPokemonService.updateAll(input, eliteFour);
        if (input.getCurrentOwnerRecordDbid() != null)
            eliteFour.setCurrentOwnerRecord(eliteFourOwnershipTermRepository.findByDbid(input.getCurrentOwnerRecordDbid()));
        else if (Boolean.TRUE.equals(input.getRemoveOwner())) {
            eliteFour.setCurrentOwnerRecord(null);
            eliteFour.getPokemon().clear();
        }
    }

    public void delete(int dbid) {
        eliteFourRepository.deleteByDbid(dbid);
    }
}