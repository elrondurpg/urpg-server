package com.pokemonurpg.service.pokemon;

import com.pokemonurpg.object.pokemon.Type;
import com.pokemonurpg.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    private TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Object> findAll() {
        return typeRepository.findAllNames();
    }

    public List<Type> findAllFull() {
        return typeRepository.findAll();
    }

    public Type findByDbid(int dbid) {
        return typeRepository.findByDbid(dbid);
    }

    public Type findByName(String name) {
        return typeRepository.findByName(name);
    }

    public List<Type> findByNameStartingWith(String name) {
        return typeRepository.findByNameStartingWith(name);
    }

    public void save(Type type) {
        typeRepository.save(type);
    }

    public void delete(Type type) {
        typeRepository.delete(type);
    }
}
