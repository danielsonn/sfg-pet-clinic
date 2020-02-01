package cz.danielson.sfgpetclinic.controller;

import cz.danielson.sfgpetclinic.exception.NotFoundException;
import cz.danielson.sfgpetclinic.model.Owner;
import cz.danielson.sfgpetclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @InjectMocks
    OwnerController ownerController;

    @Mock
    OwnerService ownerService;

    MockMvc mockMvc;

    Set<Owner> inputOwners;

    @BeforeEach
    void setUp() {
        inputOwners = new HashSet<>();
        inputOwners.add(Owner.builder().lastName("Newman").build());
        inputOwners.add(Owner.builder().lastName("Oldman").build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
        verifyNoInteractions(ownerService);
    }

    @Test
    void getOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/detail"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

    @Test
    void getOwnerNotFound() throws Exception {
        when(ownerService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/owners/10"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("/errors/404"));
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/edit"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    public void performCreationForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }


    @Test
    public void initUpdateForm() throws Exception {
        when(ownerService.findById(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/edit"))
                .andExpect(model().attributeExists("owner"));
    }


    @Test
    public void performUpdateOwner() throws Exception {

        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());

    }


    @Test
    public void findFormReturnMany() throws Exception {
        when(ownerService.findAllByLastName(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build(), Owner.builder().id(2L).build()));

        mockMvc.perform(get("/owners").param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    public void findFormReturnOne() throws Exception {
        when(ownerService.findAllByLastName(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).lastName("Newman").build()));

        mockMvc.perform(get("/owners").param("lastName", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    public void findFormReturnNone() throws Exception {
        when(ownerService.findAllByLastName(anyString())).thenReturn(new ArrayList());

        mockMvc.perform(get("/owners").param("lastName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

}