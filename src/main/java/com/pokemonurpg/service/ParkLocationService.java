package com.pokemonurpg.service;

import com.pokemonurpg.object.ParkLocation;
import com.pokemonurpg.repository.ParkLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkLocationService {

    private ParkLocationRepository parkLocationRepository;

    @Autowired
    public ParkLocationService(ParkLocationRepository parkLocationRepository) {
        this.parkLocationRepository = parkLocationRepository;
    }

    public List<Object> findAll() {
        return parkLocationRepository.findAllNames();
    }

    public ParkLocation findByDbid(int dbid) {
        return parkLocationRepository.findByDbid(dbid);
    }

    public ParkLocation findByName(String name) {
        return parkLocationRepository.findByName(name);
    }

    public List<ParkLocation> findByNameStartingWith(String name) {
        return parkLocationRepository.findByNameStartingWith(name);
    }

    public void save(ParkLocation parkLocation) {
        parkLocationRepository.save(parkLocation);
    }

    public void delete(ParkLocation parkLocation) {
        parkLocationRepository.delete(parkLocation);
    }
}
