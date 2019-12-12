package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.PetType;
import cz.danielson.sfgpetclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
}
