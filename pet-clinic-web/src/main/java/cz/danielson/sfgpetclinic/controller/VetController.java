package cz.danielson.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VetController {

    @GetMapping("/vets")
    public String listVets(Model model){
        model.addAttribute("list", "ahoj");

        return "vets/index";
    }
}
