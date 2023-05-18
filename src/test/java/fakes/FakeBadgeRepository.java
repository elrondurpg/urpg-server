package fakes;

import com.pokemonurpg.entities.v1.Badge;
import com.pokemonurpg.infrastructure.v1.data.jpa.BadgeRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class FakeBadgeRepository extends FakeNamedRepository<Badge> implements BadgeRepository {

    @Override
    public List<Badge> findAll() {
        return null;
    }

    @Override
    public List<Badge> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Badge> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Badge> findAllById(Iterable<Integer> integers) {
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
    public void delete(Badge entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Badge> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Badge> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Badge> findById(Integer integer) {
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
    public <S extends Badge> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Badge> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Badge> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Badge getOne(Integer integer) {
        return null;
    }

    @Override
    public Badge getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Badge> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Badge> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Badge> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Badge> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Badge> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Badge> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Badge, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
