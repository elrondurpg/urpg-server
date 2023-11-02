package com.pokemonurpg.species.service;

import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.species.input.TypeInputDto;
import com.pokemonurpg.species.repository.TypeRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeService implements NamedObjectService<Type> {

    @Resource
    private TypeRepository typeRepository;

    public List<String> findAllNames() {
        return typeRepository.findAllNames();
    }

    public List<Type> findAll() { return typeRepository.findAll(); }

    public Type findByDbid(int dbid) {
        return typeRepository.findByDbid(dbid);
    }

    public Type findByName(String name) {
        Type type = findByNameExact(name);
        if (type == null && name != null) {
            return typeRepository.findFirstByNameStartingWith(name);
        }
        else return type;
    }

    @Override
    public Type findByNameExact(String name) {
        return typeRepository.findByName(name);
    }

    public Type create(TypeInputDto input) {
        Type type = new Type(input);
        typeRepository.save(type);
        return type;
    }

    public Type update(TypeInputDto input, int dbid) {
        Type type = typeRepository.findByDbid(dbid);
        if (type != null) {
            type.update(input);
            typeRepository.save(type);
        }
        return type;
    }
}