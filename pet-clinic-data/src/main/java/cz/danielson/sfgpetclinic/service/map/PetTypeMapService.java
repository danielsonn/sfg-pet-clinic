package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.PetType;
import cz.danielson.sfgpetclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
}
