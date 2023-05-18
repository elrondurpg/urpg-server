package com.pokemonurpg.configuration.v1.elitefourmemberslots;

import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourMemberRecordRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourMemberSlotRepository;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.entities.v1.EliteFourMemberSlot;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EliteFourMemberSlotService implements NamedObjectService<EliteFourMemberSlot> {

    @Resource
    private EliteFourMemberSlotRepository eliteFourMemberSlotRepository;

    @Resource
    private BadgeService badgeService;

    @Resource
    private TypeService typeService;

    @Resource
    private EliteFourMemberRecordRepository eliteFourMemberRecordRepository;

    @Resource
    private EliteFourPokemonService eliteFourPokemonService;

    public List<String> findAllNames() {
        return eliteFourMemberSlotRepository.findAllNames();
    }

    public EliteFourMemberSlot findByDbid(Integer dbid) {
        return eliteFourMemberSlotRepository.findByDbid(dbid);
    }

    public EliteFourMemberSlot findByName(String name) {
        EliteFourMemberSlot eliteFourMemberSlot = findByNameExact(name);
        if (eliteFourMemberSlot == null && name != null) {
            return eliteFourMemberSlotRepository.findFirstByNameStartingWith(name);
        }
        else return eliteFourMemberSlot;
    }

    @Override
    public EliteFourMemberSlot findByNameExact(String name) {
        return eliteFourMemberSlotRepository.findByName(name);
    }

    public EliteFourMemberSlot findByCurrentOwnerRecord(EliteFourMemberRecord ownerRecord) {
        return eliteFourMemberSlotRepository.findByCurrentOwnerRecord(ownerRecord);
    }


    public EliteFourMemberSlot create(EliteFourMemberSlotRequest input) {
        EliteFourMemberSlot eliteFourMemberSlot = new EliteFourMemberSlot(input);
        updateEmbeddedValues(input, eliteFourMemberSlot);
        eliteFourMemberSlotRepository.save(eliteFourMemberSlot);
        return eliteFourMemberSlot;
    }

    public EliteFourMemberSlot update(EliteFourMemberSlotRequest input, int dbid) {
        EliteFourMemberSlot eliteFourMemberSlot = eliteFourMemberSlotRepository.findByDbid(dbid);
        if (eliteFourMemberSlot != null) {
            eliteFourMemberSlot.update(input);
            updateEmbeddedValues(input, eliteFourMemberSlot);
            eliteFourMemberSlotRepository.save(eliteFourMemberSlot);
        }
        return eliteFourMemberSlot;
    }

    public void updateCurrentOwnerRecord(EliteFourMemberSlot eliteFourMemberSlot, EliteFourMemberRecord record) {
        eliteFourMemberSlot.setCurrentOwnerRecord(record);
        eliteFourMemberSlotRepository.save(eliteFourMemberSlot);
    }

    void updateEmbeddedValues(EliteFourMemberSlotRequest input, EliteFourMemberSlot eliteFourMemberSlot) {
        eliteFourPokemonService.updateAll(input, eliteFourMemberSlot);
        if (input.getCurrentOwnerRecordDbid() != null)
            eliteFourMemberSlot.setCurrentOwnerRecord(eliteFourMemberRecordRepository.findByDbid(input.getCurrentOwnerRecordDbid()));
        else if (Boolean.TRUE.equals(input.getRemoveOwner())) {
            eliteFourMemberSlot.setCurrentOwnerRecord(null);
            eliteFourMemberSlot.getPokemon().clear();
        }
    }

    public void delete(int dbid) {
        eliteFourMemberSlotRepository.deleteByDbid(dbid);
    }
}
