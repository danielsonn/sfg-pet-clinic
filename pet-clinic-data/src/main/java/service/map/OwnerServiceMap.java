package service.map;

import cz.danielson.sfgpetclinic.model.Owner;
import org.springframework.stereotype.Service;
import service.OwnerService;

@Service
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
