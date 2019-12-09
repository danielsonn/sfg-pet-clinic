package service.map;

import cz.danielson.sfgpetclinic.model.Owner;
import service.OwnerService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Owner findByLastName() {
        return null;
    }
}
