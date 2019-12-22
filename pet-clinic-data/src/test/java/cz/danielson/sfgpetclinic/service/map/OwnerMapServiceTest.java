package cz.danielson.sfgpetclinic.service.map;

import cz.danielson.sfgpetclinic.model.Owner;
import cz.danielson.sfgpetclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    public static final long ID = 1L;
    public static final String LAST_NAME = "Newman";
    OwnerService ownerService;

    @BeforeEach
    public void setUp() {
        ownerService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        ownerService.save(Owner.builder().id(ID).lastName(LAST_NAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerService.findById(1L);
        assertNotNull(owner);
        assertEquals(1L, owner.getId());
    }

    @Test
    void saveWithId() {
        Owner owner = ownerService.save(Owner.builder().id(2L).lastName("Oldman").build());
        assertNotNull(owner);
        assertEquals(2L, owner.getId());
    }

    @Test
    void saveWithoutId() {
        Owner owner = ownerService.save(Owner.builder().build());
        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    @Test
    public void deleteById() {
        ownerService.deleteById(1L);
        Owner owner = ownerService.findById(1L);
        assertNull(owner);
    }

    @Test
    public void delete() {
        Owner owner = ownerService.findById(1L);
        ownerService.delete(owner);
        Owner owner2 = ownerService.findById(1L);
        assertNull(owner2);
    }

    @Test
    public void findByLastName() {
        Owner owner = ownerService.findByLastName(LAST_NAME);
        assertNotNull(owner);
        assertEquals(LAST_NAME, owner.getLastName());
    }

    @Test
    public void findByLastNameNoexist() {
        Owner owner = ownerService.findByLastName("NOEXISTS");
        assertNull(owner);
    }

}