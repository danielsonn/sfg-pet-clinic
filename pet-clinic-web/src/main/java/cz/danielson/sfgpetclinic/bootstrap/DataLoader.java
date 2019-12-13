package cz.danielson.sfgpetclinic.bootstrap;

import cz.danielson.sfgpetclinic.model.*;
import cz.danielson.sfgpetclinic.service.OwnerService;
import cz.danielson.sfgpetclinic.service.PetTypeService;
import cz.danielson.sfgpetclinic.service.SpecialityService;
import cz.danielson.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;
    private VetService vetService;
    private PetTypeService petTypeService;
    private SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) {

        if (petTypeService.findAll().isEmpty()) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("cat");
        PetType saveCatType = petTypeService.save(cat);

        Speciality radiologySpeciality = new Speciality();
        radiologySpeciality.setDescription("Radiology");
        specialityService.save(radiologySpeciality);

        Speciality surgerySpeciality = new Speciality();
        surgerySpeciality.setDescription("Surgery");
        specialityService.save(surgerySpeciality);

        Speciality dentisterySpeciality = new Speciality();
        dentisterySpeciality.setDescription("Dentistry");
        specialityService.save(dentisterySpeciality);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Hlavni");
        owner1.setCity("Praha");
        owner1.setTelephone("77712345");

        Pet mikePet = new Pet();
        mikePet.setPetType(saveDogType);
        mikePet.setOwner(owner1);
        mikePet.setBirthDate(LocalDate.now());
        mikePet.setName("Rocco");
        owner1.getPets().add(mikePet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner1.setAddress("123 Vedlejsi");
        owner1.setCity("Brno");
        owner1.setTelephone("777111111");

        Pet fionaCat = new Pet();
        fionaCat.setPetType(saveCatType);
        fionaCat.setOwner(owner2);
        fionaCat.setBirthDate(LocalDate.now());
        fionaCat.setName("Catty");
        owner2.getPets().add(fionaCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(dentisterySpeciality);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(radiologySpeciality);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
