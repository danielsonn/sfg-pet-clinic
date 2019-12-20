package cz.danielson.sfgpetclinic.bootstrap;

import cz.danielson.sfgpetclinic.model.*;
import cz.danielson.sfgpetclinic.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;
    private VetService vetService;
    private PetTypeService petTypeService;
    private SpecialityService specialityService;
    private VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) {

        if (petTypeService.findAll().isEmpty()) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = PetType.builder()
                .name("dog")
                .build();
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = PetType.builder()
                .name("cat")
                .build();
        PetType saveCatType = petTypeService.save(cat);

        Speciality radiologySpeciality = Speciality.builder()
                .description("Radiology")
                .build();
        specialityService.save(radiologySpeciality);

        Speciality surgerySpeciality = Speciality.builder()
                .description("Surgery")
                .build();
        specialityService.save(surgerySpeciality);

        Speciality dentisterySpeciality = Speciality.builder()
                .description("Dentistry")
                .build();
        specialityService.save(dentisterySpeciality);

        Owner owner1 = Owner.builder()
                .firstName("Michael")
                .lastName("Weston")
                .address("123 Hlavni")
                .city("Praha")
                .telephone("77712345")
                .pets(new HashSet<>())
                .build();

        Pet mikePet = Pet.builder()
                .petType(saveDogType)
                .owner(owner1)
                .birthDate(LocalDate.now())
                .name("Rocco")
                .build();
        owner1.getPets().add(mikePet);

        ownerService.save(owner1);

        Owner owner2 = Owner.builder()
                .firstName("Fiona")
                .lastName("Glenanne")
                .address("123 Vedlejsi")
                .city("Brno")
                .telephone("777111111")
                .pets(new HashSet<>())
                .build();

        Pet fionaCat = Pet.builder()
                .petType(saveCatType)
                .owner(owner2)
                .birthDate(LocalDate.now())
                .name("Catty")
                .build();
        owner2.getPets().add(fionaCat);

        ownerService.save(owner2);

        log.info("Loaded Owners....");

        Vet vet1 = Vet.builder()
                .firstName("Sam")
                .lastName("Axe")
                .specialities(new HashSet<>())
                .build();
        vet1.getSpecialities().add(dentisterySpeciality);

        vetService.save(vet1);

        Vet vet2 = Vet.builder()
                .firstName("Jessie")
                .lastName("Porter")
                .specialities(new HashSet<>())
                .build();
        vet2.getSpecialities().add(radiologySpeciality);

        vetService.save(vet2);

        Visit visit = Visit.builder()
                .pet(mikePet)
                .description("Stomach ache")
                .date(LocalDate.now())
                .build();
        visitService.save(visit);

        log.info("Loaded Vets....");
    }
}
