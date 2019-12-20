package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Speciality;
import cz.danielson.sfgpetclinic.service.SpecialityService;
import org.springframework.stereotype.Service;

@Service
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {
}