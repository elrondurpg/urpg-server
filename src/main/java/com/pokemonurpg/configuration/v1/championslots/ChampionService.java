package com.pokemonurpg.configuration.v1.championslots;

import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.infrastructure.data.ChampionOwnershipTermRepository;
import com.pokemonurpg.infrastructure.data.ChampionRepository;
import com.pokemonurpg.lib.service.NamedObjectService;
import com.pokemonurpg.entities.Champion;
import com.pokemonurpg.entities.ChampionOwnershipTerm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChampionService implements NamedObjectService<Champion> {

    @Resource
    private ChampionRepository championRepository;

    @Resource
    private BadgeService badgeService;

    @Resource
    private TypeService typeService;

    @Resource
    private ChampionOwnershipTermRepository championOwnershipTermRepository;

    @Resource
    private ChampionPokemonService championPokemonService;

    public List<String> findAllNames() {
        return championRepository.findAllNames();
    }

    public Champion findByDbid(Integer dbid) {
        return championRepository.findByDbid(dbid);
    }

    public Champion findByName(String name) {
        Champion champion = findByNameExact(name);
        if (champion == null && name != null) {
            return championRepository.findFirstByNameStartingWith(name);
        }
        else return champion;
    }

    @Override
    public Champion findByNameExact(String name) {
        return championRepository.findByName(name);
    }

    public Champion findByCurrentOwnerRecord(ChampionOwnershipTerm ownerRecord) {
        return championRepository.findByCurrentOwnerRecord(ownerRecord);
    }


    public Champion create(ChampionInputDto input) {
        Champion champion = new Champion(input);
        updateEmbeddedValues(input, champion);
        championRepository.save(champion);
        return champion;
    }

    public Champion update(ChampionInputDto input, int dbid) {
        Champion champion = championRepository.findByDbid(dbid);
        if (champion != null) {
            champion.update(input);
            updateEmbeddedValues(input, champion);
            championRepository.save(champion);
        }
        return champion;
    }

    public void updateCurrentOwnerRecord(Champion champion, ChampionOwnershipTerm record) {
        champion.setCurrentOwnerRecord(record);
        championRepository.save(champion);
    }

    void updateEmbeddedValues(ChampionInputDto input, Champion champion) {
        championPokemonService.updateAll(input, champion);
        if (input.getCurrentOwnerRecordDbid() != null)
            champion.setCurrentOwnerRecord(championOwnershipTermRepository.findByDbid(input.getCurrentOwnerRecordDbid()));
        else if (Boolean.TRUE.equals(input.getRemoveOwner())) {
            champion.setCurrentOwnerRecord(null);
            champion.getPokemon().clear();
        }
    }

    public void delete(int dbid) {
        championRepository.deleteByDbid(dbid);
    }
}
