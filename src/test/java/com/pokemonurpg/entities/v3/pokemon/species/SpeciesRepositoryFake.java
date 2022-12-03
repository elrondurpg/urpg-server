package com.pokemonurpg.entities.v3.pokemon.species;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.entities.v3.shared.PagedEntity;

import lombok.Getter;

@Getter
public class SpeciesRepositoryFake implements SpeciesRepository {
    public final static PagedEntity<SpeciesEntity> PAGE = new PagedEntity<>(Collections.singletonList(new SpeciesEntity()));

    SpeciesEntity probeArg = null;
    ExampleMatcher matcherArg = null;
    Pageable pageableArg = null;

    @Override
    @SuppressWarnings("unchecked")
    public <S extends SpeciesEntity> PagedEntity<S> findAll(Example<S> example, Pageable pageable) {
        probeArg = example.getProbe();
        matcherArg = example.getMatcher();
        pageableArg = pageable;
        return (PagedEntity<S>) PAGE;
    }

    @Override
    public SpeciesEntity findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SpeciesEntity findFirstByNameStartingWith(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SpeciesEntity findByDbid(Integer dbid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SpeciesEntity> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SpeciesEntity> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SpeciesEntity> findAllById(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SpeciesEntity> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends SpeciesEntity> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SpeciesEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<SpeciesEntity> entities) {
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
    public SpeciesEntity getOne(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SpeciesEntity getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SpeciesEntity> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SpeciesEntity> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SpeciesEntity> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<SpeciesEntity> findById(Integer id) {
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
    public void delete(SpeciesEntity entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends SpeciesEntity> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends SpeciesEntity> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends SpeciesEntity> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends SpeciesEntity> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends SpeciesEntity, R> R findBy(Example<S> example,
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
    public PagedEntity<SpeciesEntity> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
