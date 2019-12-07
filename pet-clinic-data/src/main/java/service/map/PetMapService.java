package service.map;

import cz.danielson.sfgpetclinic.model.Pet;
import service.CrudService;

public class PetMapService extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {
    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }
}
