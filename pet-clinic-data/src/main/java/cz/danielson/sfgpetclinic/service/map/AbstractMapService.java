package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.exception.NotFoundException;
import cz.danielson.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, I extends Long> {

    private Map<Long, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T findById(I id) {
        T item = map.get(id);

        if (item == null) {
            throw new NotFoundException("Recipe Not Found. For ID value: " + id);
        }

        return item;
    }

    public T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
            return object;
        } else {
            throw new RuntimeException("Object cannot be null");
        }
    }

    public void deleteById(I id) {
        map.remove(id);
    }

    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    public Long getNextId() {
        return ((map.size() > 0) ? Collections.max(map.keySet()) : 0L) + 1l;
    }
}
