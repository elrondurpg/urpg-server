package com.pokemonurpg.configuration.v1.parkranks;

import com.pokemonurpg.infrastructure.data.ParkRankRepository;
import com.pokemonurpg.entities.ParkRank;
import com.pokemonurpg.lib.service.NamedObjectService;
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
        ParkRank parkRank = findByNameExact(name);
        if (parkRank == null && name != null) {
            return parkRankRepository.findFirstByNameStartingWith(name);
        }
        else return parkRank;
    }

    @Override
    public ParkRank findByNameExact(String name) {
        return parkRankRepository.findByName(name);
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