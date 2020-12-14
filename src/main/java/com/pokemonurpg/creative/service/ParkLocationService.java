package com.pokemonurpg.creative.service;

import com.pokemonurpg.creative.models.ParkLocation;
import com.pokemonurpg.creative.input.ParkLocationInputDto;
import com.pokemonurpg.creative.repository.ParkLocationRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ParkLocationService implements NamedObjectService<ParkLocation> {

    @Resource
    private ParkLocationRepository parkLocationRepository;

    public List<String> findAllNames() {
        return parkLocationRepository.findAllNames();
    }

    public ParkLocation findByDbid(int dbid) {
        return parkLocationRepository.findByDbid(dbid);
    }

    public ParkLocation findByName(String name) {
        ParkLocation parkLocation = parkLocationRepository.findByName(name);
        if (parkLocation == null && name != null) {
            return parkLocationRepository.findFirstByNameStartingWith(name);
        }
        else return parkLocation;
    }

    public ParkLocation create(ParkLocationInputDto input) {
        ParkLocation parkLocation = new ParkLocation(input);
        parkLocationRepository.save(parkLocation);
        return parkLocation;
    }

    public ParkLocation update(ParkLocationInputDto input, int dbid) {
        ParkLocation parkLocation = parkLocationRepository.findByDbid(dbid);
        if (parkLocation != null) {
            parkLocation.update(input);
            parkLocationRepository.save(parkLocation);
        }
        return parkLocation;
    }
}
