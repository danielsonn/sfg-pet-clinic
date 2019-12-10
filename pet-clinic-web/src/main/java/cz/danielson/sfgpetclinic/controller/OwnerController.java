package cz.danielson.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cz.danielson.sfgpetclinic.service.OwnerService;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("")
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "/owners/index";
    }
}
