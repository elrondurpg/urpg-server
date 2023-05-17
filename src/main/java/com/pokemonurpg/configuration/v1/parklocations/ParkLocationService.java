package com.pokemonurpg.configuration.v1.parklocations;

import com.pokemonurpg.infrastructure.data.ParkLocationRepository;
import com.pokemonurpg.entities.ParkLocation;
import com.pokemonurpg.lib.service.NamedObjectService;
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
        ParkLocation parkLocation = findByNameExact(name);
        if (parkLocation == null && name != null) {
            return parkLocationRepository.findFirstByNameStartingWith(name);
        }
        else return parkLocation;
    }

    @Override
    public ParkLocation findByNameExact(String name) {
        return parkLocationRepository.findByName(name);
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