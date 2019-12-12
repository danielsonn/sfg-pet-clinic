package cz.danielson.sfgpetclinic.bootstrap;

import cz.danielson.sfgpetclinic.model.Owner;
import cz.danielson.sfgpetclinic.model.Pet;
import cz.danielson.sfgpetclinic.model.PetType;
import cz.danielson.sfgpetclinic.model.Vet;
import cz.danielson.sfgpetclinic.service.PetTypeService;
import org.springframework.boot.CommandLineRunner;
import cz.danielson.sfgpetclinic.service.OwnerService;
import cz.danielson.sfgpetclinic.service.VetService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;
    private VetService vetService;
    private PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;

    }

    @Override
    public void run(String... args) {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("cat");
        PetType saveCatType = petTypeService.save(cat);


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

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
