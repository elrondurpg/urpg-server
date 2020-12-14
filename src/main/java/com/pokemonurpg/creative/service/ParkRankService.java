package com.pokemonurpg.creative.service;

import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.input.ParkRankInputDto;
import com.pokemonurpg.creative.repository.ParkRankRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ParkRankService implements NamedObjectService<ParkRank> {

    @Resource
    private ParkRankRepository parkRankRepository;

    public List<String> findAllNames() {
        return parkRankRepository.findAllNames();
    }

    public ParkRank findByDbid(int dbid) {
        return parkRankRepository.findByDbid(dbid);
    }

    public ParkRank findByName(String name) {
        ParkRank parkRank = parkRankRepository.findByName(name);
        if (parkRank == null && name != null) {
            return parkRankRepository.findFirstByNameStartingWith(name);
        }
        else return parkRank;
    }

    public ParkRank create(ParkRankInputDto input) {
        ParkRank parkRank = new ParkRank(input);
        parkRankRepository.save(parkRank);
        return parkRank;
    }

    public ParkRank update(ParkRankInputDto input, int dbid) {
        ParkRank parkRank = parkRankRepository.findByDbid(dbid);
        if (parkRank != null) {
            parkRank.update(input);
            parkRankRepository.save(parkRank);
        }
        return parkRank;
    }
}
