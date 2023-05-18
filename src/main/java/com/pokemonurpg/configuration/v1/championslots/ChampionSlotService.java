package com.pokemonurpg.configuration.v1.championslots;

import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionRecordRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionSlotRepository;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.entities.v1.ChampionSlot;
import com.pokemonurpg.entities.v1.ChampionRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChampionSlotService implements NamedObjectService<ChampionSlot> {

    @Resource
    private ChampionSlotRepository championSlotRepository;

    @Resource
    private BadgeService badgeService;

    @Resource
    private TypeService typeService;

    @Resource
    private ChampionRecordRepository championRecordRepository;

    @Resource
    private ChampionPokemonService championPokemonService;

    public List<String> findAllNames() {
        return championSlotRepository.findAllNames();
    }

    public ChampionSlot findByDbid(Integer dbid) {
        return championSlotRepository.findByDbid(dbid);
    }

    public ChampionSlot findByName(String name) {
        ChampionSlot championSlot = findByNameExact(name);
        if (championSlot == null && name != null) {
            return championSlotRepository.findFirstByNameStartingWith(name);
        }
        else return championSlot;
    }

    @Override
    public ChampionSlot findByNameExact(String name) {
        return championSlotRepository.findByName(name);
    }

    public ChampionSlot findByCurrentOwnerRecord(ChampionRecord ownerRecord) {
        return championSlotRepository.findByCurrentOwnerRecord(ownerRecord);
    }


    public ChampionSlot create(ChampionSlotRequest input) {
        ChampionSlot championSlot = new ChampionSlot(input);
        updateEmbeddedValues(input, championSlot);
        championSlotRepository.save(championSlot);
        return championSlot;
    }

    public ChampionSlot update(ChampionSlotRequest input, int dbid) {
        ChampionSlot championSlot = championSlotRepository.findByDbid(dbid);
        if (championSlot != null) {
            championSlot.update(input);
            updateEmbeddedValues(input, championSlot);
            championSlotRepository.save(championSlot);
        }
        return championSlot;
    }

    public void updateCurrentOwnerRecord(ChampionSlot championSlot, ChampionRecord record) {
        championSlot.setCurrentOwnerRecord(record);
        championSlotRepository.save(championSlot);
    }

    void updateEmbeddedValues(ChampionSlotRequest input, ChampionSlot championSlot) {
        championPokemonService.updateAll(input, championSlot);
        if (input.getCurrentOwnerRecordDbid() != null)
            championSlot.setCurrentOwnerRecord(championRecordRepository.findByDbid(input.getCurrentOwnerRecordDbid()));
        else if (Boolean.TRUE.equals(input.getRemoveOwner())) {
            championSlot.setCurrentOwnerRecord(null);
            championSlot.getPokemon().clear();
        }
    }

    public void delete(int dbid) {
        championSlotRepository.deleteByDbid(dbid);
    }
}
