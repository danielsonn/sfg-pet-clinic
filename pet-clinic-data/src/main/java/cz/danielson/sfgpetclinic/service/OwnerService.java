package cz.danielson.sfgpetclinic.service;

import cz.danielson.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastName(String lastName);
}
