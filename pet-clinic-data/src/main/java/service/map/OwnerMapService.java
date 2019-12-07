package service.map;

import cz.danielson.sfgpetclinic.model.Owner;
import service.CrudService;

public class OwnerMapService extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {
    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

}
