package cz.danielson.sfgpetclinic.controller;

import cz.danielson.sfgpetclinic.model.Owner;
import cz.danielson.sfgpetclinic.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/owners")
@Slf4j
public class OwnerController {

    public static final String OWNER = "owner";
    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute(OWNER, Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        List<Owner> owners = ownerService.findAllByLastName(owner.getLastName());
        switch (owners.size()) {
            case 0:
                bindingResult.rejectValue("lastName", "notFound", "not found");
                return "owners/findOwners";
            case 1:
                model.addAttribute(OWNER, owners.get(0));
                return "redirect:/owners/" + owners.get(0).getId();
            default:
                model.addAttribute("owners", owners);
                return "/owners/index";
        }

    }

    @GetMapping("/{id}")
    public String getOwner(@PathVariable Long id, Model model) {
        model.addAttribute(OWNER, ownerService.findById(id));
        return "/owners/detail";
    }
}
