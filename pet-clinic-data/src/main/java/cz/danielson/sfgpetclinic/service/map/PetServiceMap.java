package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Pet;
import org.springframework.stereotype.Service;
import cz.danielson.sfgpetclinic.service.PetService;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }
}
