package cz.danielson.sfgpetclinic.controller;

import cz.danielson.sfgpetclinic.model.Owner;
import cz.danielson.sfgpetclinic.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
@Slf4j
public class OwnerController {

    Logger logger = LoggerFactory.getLogger(OwnerController.class);

    public static final String OWNERS_CREATE_OR_UPDATE_FORM = "/owners/edit";
    public static final String REDIRECT_OWNERS = "redirect:/owners/";
    public static final String OWNERS_FIND_OWNERS = "owners/findOwners";
    public static final String OWNER = "owner";

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute(OWNER, Owner.builder().build());
        return OWNERS_FIND_OWNERS;
    }

    @GetMapping
    public String processFindForm(@Valid Owner owner, BindingResult bindingResult, Model model) {
        List<Owner> owners = ownerService.findAllByLastName(owner.getLastName());
        switch (owners.size()) {
            case 0:
                bindingResult.rejectValue("lastName", "notFound", "not found");
                return OWNERS_FIND_OWNERS;
            case 1:
                model.addAttribute(OWNER, owners.get(0));
                return REDIRECT_OWNERS + owners.get(0).getId();
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

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute(OWNER, new Owner());
        return OWNERS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreate(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return OWNERS_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return REDIRECT_OWNERS + savedOwner.getId();
        }
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable Long id, Model model) {
        Owner owner = ownerService.findById(id);
        if (owner == null) {
            return "redirect:/notfound";
        }
        model.addAttribute(OWNER, owner);
        return OWNERS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{id}/edit")
    public String processUpdate(@Valid Owner owner, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "/owners/" + owner.getId() + "/edit";
        } else {
            owner.setId(id);
            Owner savedOwner = ownerService.save(owner);
            return REDIRECT_OWNERS + savedOwner.getId();
        }
    }

}
