package cz.danielson.sfgpetclinic.repository;

import cz.danielson.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
