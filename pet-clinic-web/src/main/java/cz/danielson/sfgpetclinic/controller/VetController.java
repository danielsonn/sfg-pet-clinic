package cz.danielson.sfgpetclinic.controller;

import cz.danielson.sfgpetclinic.model.Vet;
import cz.danielson.sfgpetclinic.service.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping("")
@Slf4j
public class VetController {

    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("/vets")
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> listVetsJson(){
        return vetService.findAll();
    }
}
