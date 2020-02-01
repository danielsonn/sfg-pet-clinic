package cz.danielson.sfgpetclinic.controller;

import cz.danielson.sfgpetclinic.model.Pet;
import cz.danielson.sfgpetclinic.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/pets")
@Slf4j
public class PetController {

    public static final String PET = "pet";
    public static final String EDIT_FORM = "/pets/edit";
    public static final String REDIRECT_DETAIL = "redirect:/pets/";

    PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute(PET, petService.findAll());
        return "/pets/index";
    }

    @GetMapping("/{id}")
    public String getPet(@PathVariable Long id, Model model) {
        model.addAttribute(PET, petService.findById(id));
        return "/pets/detail";
    }

    @GetMapping("/new")
    public String create() {
        return EDIT_FORM;
    }

    @PostMapping("/new")
    public String processCreate(@Valid Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return EDIT_FORM;
        }
        Pet savedPet = petService.save(pet);
        return REDIRECT_DETAIL + savedPet.getId();
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Pet pet = petService.findById(id);
        if (pet == null) {
            return "redirect:/pets/notfound";
        }
        model.addAttribute(PET, pet);
        return EDIT_FORM;
    }

    @PostMapping("/update/{id}")
    public String processUpdate(@Valid Pet pet, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            result.rejectValue("id", "notFound", "not found");
            return EDIT_FORM;
        }else{
            pet.setId(id);
            petService.save(pet);
            return REDIRECT_DETAIL+pet.getId();
        }

    }
}
