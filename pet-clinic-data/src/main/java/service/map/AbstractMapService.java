package service.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, I> {

    private Map<I, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T findById(I id) {
        return map.get(id);
    }

    public T save(I id, T object) {
        map.put(id, object);

        return object;
    }

    public void deleteById(I id) {
        map.remove(id);
    }

    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
}
