package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Vet;
import cz.danielson.sfgpetclinic.service.SpecialityService;
import cz.danielson.sfgpetclinic.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
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
