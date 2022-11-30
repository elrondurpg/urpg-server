package com.pokemonurpg.member.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.pokemonurpg.member.models.KnownNameClaim;

public class KnownNameClaimRepositoryFake implements KnownNameClaimRepository {
    public final static String CLAIMED_NAME = "CLAIMED_NAME";
    public final static String UNCLAIMED_NAME = "UNCLAIMED_NAME";

    @Override
    public KnownNameClaim findByName(String name) {
        if (CLAIMED_NAME.equals(name)) {
            return new KnownNameClaim();
        }
        else {
            return null;
        }
    }

    @Override
    public List<KnownNameClaim> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<KnownNameClaim> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<KnownNameClaim> findAllById(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends KnownNameClaim> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends KnownNameClaim> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends KnownNameClaim> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<KnownNameClaim> entities) {
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
    public KnownNameClaim getOne(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public KnownNameClaim getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends KnownNameClaim> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends KnownNameClaim> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<KnownNameClaim> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends KnownNameClaim> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<KnownNameClaim> findById(Integer id) {
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
    public void delete(KnownNameClaim entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends KnownNameClaim> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends KnownNameClaim> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends KnownNameClaim> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends KnownNameClaim> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends KnownNameClaim> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends KnownNameClaim, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public KnownNameClaim findByDiscordId(String discordId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
