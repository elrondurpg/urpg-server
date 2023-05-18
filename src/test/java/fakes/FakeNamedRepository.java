package fakes;

import com.pokemonurpg.lib.v1.models.NamedObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FakeNamedRepository<T extends NamedObject> extends FakeIndexedRepository<T> {
    protected Map<String, T> entitiesByName = new HashMap<>();
    public <S extends T> S save(S entity) {
        super.save(entity);
        if (entity.getName() != null) {
            entitiesByName.put(entity.getName(), entity);
        }
        return entity;
    }
    public List<String> findAllNames() {
        return entitiesByName.values().stream().map(T::getName).collect(Collectors.toList());
    }

    public T findByName(String name) {
        return entitiesByName.get(name);
    }

    public T findFirstByNameStartingWith(String name) {
        return entitiesByName.values().stream().filter(entity -> entity.getName().startsWith(name)).findFirst().orElse(null);
    }
}
