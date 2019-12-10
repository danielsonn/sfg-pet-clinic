package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Owner;
import cz.danielson.sfgpetclinic.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

   @Override
    public Owner findByLastName() {
        return null;
    }
}
