package service.map;

import cz.danielson.sfgpetclinic.model.Vet;
import service.CrudService;

public class VetMapService extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {
    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }
}
