package fakes;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymOwnershipTerm;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class FakeGymRepository extends FakeNamedRepository<Gym> implements GymRepository {

    Map<GymOwnershipTerm, Gym> entitiesByCurrentOwnerRecord = new HashMap<>();

    @Override
    public <S extends Gym> S save(S entity) {
        super.save(entity);
        if (entity.getCurrentOwnerRecord() != null) {
            entitiesByCurrentOwnerRecord.put(entity.getCurrentOwnerRecord(), entity);
        }
        return entity;
    }

    @Override
    public Gym findByCurrentOwnerRecord(GymOwnershipTerm currentOwnerRecord) {
        return entitiesByCurrentOwnerRecord.get(currentOwnerRecord);
    }

    @Override
    public List<Gym> findAll() {
        return null;
    }

    @Override
    public List<Gym> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Gym> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Gym> findAllById(Iterable<Integer> integers) {
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
    public void delete(Gym entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Gym> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Gym> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Gym> findById(Integer integer) {
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
    public <S extends Gym> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Gym> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Gym> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Gym getOne(Integer integer) {
        return null;
    }

    @Override
    public Gym getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Gym> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Gym> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Gym> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Gym> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Gym> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Gym> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Gym, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
