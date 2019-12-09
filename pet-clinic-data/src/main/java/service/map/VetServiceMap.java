package service.map;

import cz.danielson.sfgpetclinic.model.Vet;
import org.springframework.stereotype.Service;
import service.VetService;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }
}
