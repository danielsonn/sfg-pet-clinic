package service.map;

import cz.danielson.sfgpetclinic.model.Vet;
import service.VetService;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }
}
