package com.pokemonurpg.entities.v3.pokemon.species;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.entities.v1.pokemon.species.Species;
import com.pokemonurpg.entities.v1.pokemon.species.SpeciesRepository;

import lombok.Getter;

@Getter
public class SpeciesRepositoryFake implements SpeciesRepository {
    public final static PageImpl<Species> PAGE = new PageImpl<>(Collections.singletonList(new Species()));

    Species probeArg = null;
    ExampleMatcher matcherArg = null;
    Pageable pageableArg = null;

    @Override
    @SuppressWarnings("unchecked")
    public <S extends Species> PageImpl<S> findAll(Example<S> example, Pageable pageable) {
        probeArg = example.getProbe();
        matcherArg = example.getMatcher();
        pageableArg = pageable;
        return (PageImpl<S>) PAGE;
    }

    @Override
    public Species findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Species findFirstByNameStartingWith(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Species findByDbid(Integer dbid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Species> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Species> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Species> findAllById(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Species> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends Species> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Species> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Species> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Species getOne(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Species getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Species> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Species> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Species> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Species> findById(Integer id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Species entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends Species> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends Species> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends Species> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Species> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends Species, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer findMaxDexno() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Species> findByDexno(Integer dexno) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Species findFirstByDexno(int dexno) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Species> findByPreEvolution(Species species) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Species> findByPreMega(Species species) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PageImpl<Species> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
