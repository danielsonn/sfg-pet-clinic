package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Owner;
import cz.danielson.sfgpetclinic.service.OwnerService;
import cz.danielson.sfgpetclinic.service.PetService;
import cz.danielson.sfgpetclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    PetService petService;
    PetTypeService petTypeService;

    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> lastName.equals(owner.getLastName()))
                .findFirst()
                .orElse(null);
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
