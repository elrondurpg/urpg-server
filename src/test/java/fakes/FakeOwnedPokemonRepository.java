package fakes;

import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.infrastructure.v1.data.jpa.OwnedPokemonRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class FakeOwnedPokemonRepository extends FakeIndexedRepository<OwnedPokemon> implements OwnedPokemonRepository {
    @Override
    public List<OwnedPokemon> findByTrainer(Member trainer) {
        return null;
    }

    @Override
    public List<OwnedPokemon> findAll() {
        return null;
    }

    @Override
    public List<OwnedPokemon> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<OwnedPokemon> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<OwnedPokemon> findAllById(Iterable<Integer> integers) {
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
    public void delete(OwnedPokemon entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends OwnedPokemon> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends OwnedPokemon> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<OwnedPokemon> findById(Integer integer) {
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
    public <S extends OwnedPokemon> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends OwnedPokemon> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<OwnedPokemon> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public OwnedPokemon getOne(Integer integer) {
        return null;
    }

    @Override
    public OwnedPokemon getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends OwnedPokemon> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends OwnedPokemon> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends OwnedPokemon> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends OwnedPokemon> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends OwnedPokemon> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends OwnedPokemon> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends OwnedPokemon, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
