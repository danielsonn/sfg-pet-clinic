package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Visit;
import cz.danielson.sfgpetclinic.service.VisitService;
import org.springframework.stereotype.Service;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Visit save(Visit visit) {
        if (visit.getPet() == null || visit.getPet().getId() == null || visit.getPet().getOwner() == null || visit.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invelid visit");
        }
        return super.save(visit);
    }
}
