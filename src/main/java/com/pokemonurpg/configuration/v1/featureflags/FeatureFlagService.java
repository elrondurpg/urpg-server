package com.pokemonurpg.configuration.v1.featureflags;

import com.pokemonurpg.infrastructure.v1.data.jpa.FeatureFlagRepository;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.entities.v1.FeatureFlag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeatureFlagService implements NamedObjectService<FeatureFlag> {

    @Resource
    private FeatureFlagRepository featureFlagRepository;

    public List<String> findAllNames() {
        return featureFlagRepository.findAllNames();
    }

    public FeatureFlag findByDbid(int dbid) {
        return featureFlagRepository.findByDbid(dbid);
    }

    public FeatureFlag findByName(String name) {
        FeatureFlag featureFlag = findByNameExact(name);
        if (featureFlag == null && name != null) {
            return featureFlagRepository.findFirstByNameStartingWith(name);
        }
        else return featureFlag;
    }

    @Override
    public FeatureFlag findByNameExact(String name) {
        return featureFlagRepository.findByName(name);
    }

    public FeatureFlag create(FeatureFlagRequest input) {
        FeatureFlag featureFlag = new FeatureFlag(input);
        featureFlagRepository.save(featureFlag);
        return featureFlag;
    }

    public FeatureFlag update(FeatureFlagRequest input, int dbid) {
        FeatureFlag featureFlag = featureFlagRepository.findByDbid(dbid);
        if (featureFlag != null) {
            featureFlag.update(input);
            featureFlagRepository.save(featureFlag);
        }
        return featureFlag;
    }
}
