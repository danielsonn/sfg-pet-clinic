package cz.danielson.sfgpetclinic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    @GetMapping({"/", "", "index", "index.html"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/oups")
    public String getOups() {
        return "notimplemented";
    }
}
