package cz.danielson.sfgpetclinic.controller;

import cz.danielson.sfgpetclinic.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
@Slf4j
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("")
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "/owners/index";
    }

    @GetMapping("/find")
    public String findOwners() {
        return "notimplemented";
    }

    @GetMapping("/{id}")
    public String getOwner(@PathVariable Long id, Model model) {
        model.addAttribute("owner", ownerService.findById(id));
        return "/owners/detail";
    }
}
