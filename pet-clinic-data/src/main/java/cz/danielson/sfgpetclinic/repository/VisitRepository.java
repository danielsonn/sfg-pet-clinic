package cz.danielson.sfgpetclinic.repository;

import cz.danielson.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
