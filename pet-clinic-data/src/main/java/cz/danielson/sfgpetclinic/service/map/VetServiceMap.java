package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Vet;
import cz.danielson.sfgpetclinic.service.SpecialityService;
import cz.danielson.sfgpetclinic.service.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet save(Vet object) {
        if (!object.getSpecialities().isEmpty()) {
            object.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null) {
                    specialityService.save(speciality);
                }
            });
        }
        return super.save(object);
    }
}
