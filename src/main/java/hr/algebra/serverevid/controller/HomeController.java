package hr.algebra.serverevid.controller;

import hr.algebra.serverevid.service.IntervencijaService;
import hr.algebra.serverevid.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ServerService serverService;

    @Autowired
    private IntervencijaService intervencijaService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("brojServera", serverService.ukupnoBroj());
        model.addAttribute("brojIntervencija", intervencijaService.ukupnoBroj());
        model.addAttribute("serveri", serverService.dohvatiSve());
        return "index";
    }
}
