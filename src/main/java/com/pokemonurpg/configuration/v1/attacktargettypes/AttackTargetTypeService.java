package com.pokemonurpg.configuration.v1.attacktargettypes;

import com.pokemonurpg.infrastructure.v1.data.jpa.AttackTargetTypeRepository;
import com.pokemonurpg.lib.v1.service.NamedObjectService;
import com.pokemonurpg.entities.v1.AttackTargetType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttackTargetTypeService implements NamedObjectService<AttackTargetType> {

    @Resource
    private AttackTargetTypeRepository attackTargetTypeRepository;

    public List<String> findAllNames() {
        return attackTargetTypeRepository.findAllNames();
    }

    public AttackTargetType findByDbid(int dbid) {
        return attackTargetTypeRepository.findByDbid(dbid);
    }

    public AttackTargetType findByName(String name) {
        AttackTargetType attackTargetType = attackTargetTypeRepository.findByName(name);
        if (attackTargetType == null && name != null) {
            return attackTargetTypeRepository.findFirstByNameStartingWith(name);
        }
        else return attackTargetType;
    }

    @Override
    public AttackTargetType findByNameExact(String name) {
        return attackTargetTypeRepository.findByName(name);
    }

    public AttackTargetType create(AttackTargetTypeInputDto input) {
        AttackTargetType attackTargetType = new AttackTargetType(input);
        attackTargetTypeRepository.save(attackTargetType);
        return attackTargetType;
    }

    public AttackTargetType update(AttackTargetTypeInputDto input, int dbid) {
        AttackTargetType attackTargetType = attackTargetTypeRepository.findByDbid(dbid);
        if (attackTargetType != null) {
            attackTargetType.update(input);
            attackTargetTypeRepository.save(attackTargetType);
        }
        return attackTargetType;
    }
}
