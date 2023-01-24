package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.Getter;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

@Getter
public class JpaTypeRepositoryFake implements JpaTypeRepository {
    public final static int EXISTING_DBID = 123;
    public final static String EXISTING_NAME = "EXISTING_NAME";
    public final static int NOT_FOUND_DBID = 234;
    public final static String NOT_FOUND_NAME = "NOT_FOUND_NAME";

    public final static int PAGE_NUMBER_NOT_FOUND = 11;
    public final static int PAGE_SIZE = 10;
    public final static int PAGE_NUMBER = 1;
    public final static int TOTAL_SORTED_ITEMS = 11;
    public final static int TOTAL_ITEMS = 12;
    public final static String SORT_BY = "NAME";

    private Type savedObject;

    @Override
    public <S extends JpaTypeModel> S save(S entity) {
        savedObject = entity;
        return entity;
    }

    @Override
    public boolean existsByDbid(int dbid) {
        return EXISTING_DBID == dbid;
    }

    @Override
    public boolean existsByName(String name) {
        return EXISTING_NAME.equals(name);
    }

    @Override
    public JpaTypeModel findByDbid(int dbid) {
        if (EXISTING_DBID == dbid) {
            return createExistingType();
        }
        else {
            return null;
        }
    }

    @Override
    public JpaTypeModel findByName(String name) {
        if (EXISTING_NAME.equals(name)) {
            return createExistingType();
        }
        else {
            return null;
        }
    }

    @Override
    public JpaTypeModel findFirstByNameStartingWith(String begin) {
        if (EXISTING_NAME.startsWith(begin)) {
            return createExistingType();
        }
        else {
            return null;
        }
    }

    @Override
    public JpaTypeModel deleteByDbid(int dbid) {
        if (EXISTING_DBID == dbid) {
            return createExistingType();
        }
        else {
            return null;
        }
    }

    @Override
    public Page<JpaTypeModel> findAll(Pageable pageable) {
        if (PAGE_NUMBER == pageable.getPageNumber()
                && PAGE_SIZE == pageable.getPageSize()
                && pageable.getSort().getOrderFor(SORT_BY) != null) {
            Pageable pageInfo = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
            return new PageImpl<>(Collections.singletonList(createExistingType()), pageInfo, TOTAL_SORTED_ITEMS);
        }
        else if (PAGE_NUMBER == pageable.getPageNumber()
                && PAGE_SIZE == pageable.getPageSize()) {
            Pageable pageInfo = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
            return new PageImpl<>(Arrays.asList(createExistingType(), createExistingType()), pageInfo, TOTAL_ITEMS);
        }
        else {
            return Page.empty();
        }
    }

    private JpaTypeModel createExistingType() {
        JpaTypeModel entity = new JpaTypeModel();
        entity.setDbid(EXISTING_DBID);
        entity.setName(EXISTING_NAME);
        return entity;
    }

    @Override
    public List<JpaTypeModel> findAll() {
        return null;
    }

    @Override
    public List<JpaTypeModel> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<JpaTypeModel> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(JpaTypeModel entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends JpaTypeModel> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends JpaTypeModel> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<JpaTypeModel> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends JpaTypeModel> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends JpaTypeModel> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<JpaTypeModel> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public JpaTypeModel getOne(Integer integer) {
        return null;
    }

    @Override
    public JpaTypeModel getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends JpaTypeModel> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends JpaTypeModel> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends JpaTypeModel> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends JpaTypeModel> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends JpaTypeModel> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends JpaTypeModel> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends JpaTypeModel, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
