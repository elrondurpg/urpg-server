package fakes;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeaderRecord;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymLeaderRecordRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class FakeGymRecordRepository extends FakeIndexedRepository<GymLeaderRecord> implements GymLeaderRecordRepository {

    @Override
    public GymLeaderRecord findByGymAndOwnerAndOpenDate(Gym gym, Member owner, Date openDate) {
        return null;
    }

    @Override
    public List<GymLeaderRecord> findAll() {
        return null;
    }

    @Override
    public List<GymLeaderRecord> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<GymLeaderRecord> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<GymLeaderRecord> findAllById(Iterable<Integer> integers) {
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
    public void delete(GymLeaderRecord entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends GymLeaderRecord> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends GymLeaderRecord> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<GymLeaderRecord> findById(Integer integer) {
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
    public <S extends GymLeaderRecord> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends GymLeaderRecord> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<GymLeaderRecord> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public GymLeaderRecord getOne(Integer integer) {
        return null;
    }

    @Override
    public GymLeaderRecord getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends GymLeaderRecord> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends GymLeaderRecord> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends GymLeaderRecord> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends GymLeaderRecord> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends GymLeaderRecord> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends GymLeaderRecord> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends GymLeaderRecord, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
