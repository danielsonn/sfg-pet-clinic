package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Pet;
import org.springframework.stereotype.Service;
import cz.danielson.sfgpetclinic.service.PetService;

@Service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
}
