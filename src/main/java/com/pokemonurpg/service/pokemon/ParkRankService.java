package com.pokemonurpg.service.pokemon;

import com.pokemonurpg.object.pokemon.ParkRank;
import com.pokemonurpg.repository.ParkRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkRankService {

    private ParkRankRepository parkRankRepository;

    @Autowired
    public ParkRankService(ParkRankRepository parkRankRepository) {
        this.parkRankRepository = parkRankRepository;
    }

    public List<Object> findAll() {
        return parkRankRepository.findAllNames();
    }

    public ParkRank findByDbid(int dbid) {
        return parkRankRepository.findByDbid(dbid);
    }

    public ParkRank findByName(String name) {
        return parkRankRepository.findByName(name);
    }

    public List<ParkRank> findByNameStartingWith(String name) {
        return parkRankRepository.findByNameStartingWith(name);
    }

    public void save(ParkRank parkRank) {
        parkRankRepository.save(parkRank);
    }

    public void delete(ParkRank parkRank) {
        parkRankRepository.delete(parkRank);
    }
}
