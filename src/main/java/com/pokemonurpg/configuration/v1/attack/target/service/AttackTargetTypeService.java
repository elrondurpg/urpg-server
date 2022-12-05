package com.pokemonurpg.configuration.v1.attack.target.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.attack.target.input.AttackTargetTypeInputDto;
import com.pokemonurpg.entities.v1.attack.AttackTargetType;
import com.pokemonurpg.entities.v1.attack.AttackTargetTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttackTargetTypeService extends NamedConfigurationService<AttackTargetType, AttackTargetTypeInputDto> {

    @Autowired
    public AttackTargetTypeService(AttackTargetTypeRepository repo) {
        super(repo, AttackTargetType.class);
    }

    @Override
    public void updateBase(AttackTargetType model, AttackTargetTypeInputDto input) {
        super.updateBase(model, input);
        setIfNotNull(input.getDescription(), model::setDescription);
    }

    @Override
    protected void updateEmbeddedValues(AttackTargetType model, AttackTargetTypeInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void updateAssociatedValues(AttackTargetType model, AttackTargetTypeInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(AttackTargetType model) {
        // TODO Auto-generated method stub
        
    }
}
