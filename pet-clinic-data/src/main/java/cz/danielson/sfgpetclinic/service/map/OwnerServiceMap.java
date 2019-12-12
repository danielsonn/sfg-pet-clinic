package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Owner;
import cz.danielson.sfgpetclinic.service.OwnerService;
import cz.danielson.sfgpetclinic.service.PetService;
import cz.danielson.sfgpetclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    PetService petService;
    PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findByLastName() {
        return null;
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            petTypeService.save(pet.getPetType());
                        }
                    } else {
                        throw new RuntimeException("Pet Type is Required");
                    }

                    if (pet.getId() == null) {
                        petService.save(pet);
                    }
                });
            }
            return super.save(object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }
    }
}
