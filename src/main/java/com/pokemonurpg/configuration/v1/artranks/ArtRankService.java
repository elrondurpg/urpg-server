package com.pokemonurpg.configuration.v1.artranks;

import com.pokemonurpg.infrastructure.data.ArtRankRepository;
import com.pokemonurpg.entities.ArtRank;
import com.pokemonurpg.lib.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArtRankService implements NamedObjectService<ArtRank> {

    @Resource
    private ArtRankRepository artRankRepository;

    public List<String> findAllNames() {
        return artRankRepository.findAllNames();
    }

    public ArtRank findByDbid(int dbid) {
        return artRankRepository.findByDbid(dbid);
    }

    public ArtRank findByName(String name) {
        ArtRank artRank = findByNameExact(name);
        if (artRank == null && name != null) {
            return artRankRepository.findFirstByNameStartingWith(name);
        }
        else return artRank;
    }

    @Override
    public ArtRank findByNameExact(String name) {
        return artRankRepository.findByName(name);
    }

    public ArtRank create(ArtRankInputDto input) {
        ArtRank artRank = new ArtRank(input);
        artRankRepository.save(artRank);
        return artRank;
    }

    public ArtRank update(ArtRankInputDto input, int dbid) {
        ArtRank artRank = artRankRepository.findByDbid(dbid);
        if (artRank != null) {
            artRank.update(input);
            artRankRepository.save(artRank);
        }
        return artRank;
    }
}
