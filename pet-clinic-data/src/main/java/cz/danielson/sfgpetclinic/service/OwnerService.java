package cz.danielson.sfgpetclinic.service;

import cz.danielson.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName();
}
