package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Speciality;
import cz.danielson.sfgpetclinic.service.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {
}
