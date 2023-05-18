package fakes;

import com.pokemonurpg.lib.v1.models.IndexedObject;

import java.util.HashMap;
import java.util.Map;

public class FakeIndexedRepository<T extends IndexedObject>{
    protected Map<Integer, T> entitiesByDbid = new HashMap<>();
    public <S extends T> S save(S entity) {
        if (entity.getDbid() != null) {
            entitiesByDbid.put(entity.getDbid(), entity);
        }
        return entity;
    }

    public T findByDbid(Integer dbid) {
        return entitiesByDbid.get(dbid);
    }

    public void deleteByDbid(int dbid) {
        entitiesByDbid.remove(dbid);
    }
}
