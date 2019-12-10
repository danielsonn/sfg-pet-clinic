package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Vet;
import cz.danielson.sfgpetclinic.service.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
}
