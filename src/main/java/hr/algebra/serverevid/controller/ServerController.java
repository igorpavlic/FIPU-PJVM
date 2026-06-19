package hr.algebra.serverevid.controller;

import hr.algebra.serverevid.model.Server;
import hr.algebra.serverevid.service.IntervencijaService;
import hr.algebra.serverevid.service.ServerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/servers")
public class ServerController {

    @Autowired
    private ServerService serverService;

    @Autowired
    private IntervencijaService intervencijaService;

    // popis svih servera
    @GetMapping
    public String popis(@RequestParam(required = false) String query,
                        @RequestParam(required = false, defaultValue = "naziv") String sort,
                        Model model) {

        List<Server> serveri;

        if (query != null && !query.isEmpty()) {
            serveri = serverService.pretrazi(query);
        } else {
            serveri = serverService.dohvatiSve();
        }

        model.addAttribute("serveri", serveri);
        model.addAttribute("query", query);
        model.addAttribute("sort", sort);
        return "servers/list";
    }

    // detalji jednog servera
    @GetMapping("/{id}")
    public String detalji(@PathVariable Long id, Model model) {
        Server server = serverService.dohvatiPoid(id).orElse(null);

        if (server == null) {
            return "redirect:/servers";
        }

        model.addAttribute("server", server);
        model.addAttribute("intervencije", intervencijaService.dohvatiZaServer(id));
        return "servers/detail";
    }

    // forma za novi server
    @GetMapping("/new")
    public String novaForma(Model model) {
        model.addAttribute("server", new Server());
        return "servers/form";
    }

    // spremi novi server
    @PostMapping
    public String spremi(@Valid @ModelAttribute Server server,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "servers/form";
        }

        serverService.spremi(server);
        redirectAttributes.addFlashAttribute("poruka", "Server uspješno dodan!");
        return "redirect:/servers";
    }

    // forma za uredivanje
    @GetMapping("/{id}/edit")
    public String urediForma(@PathVariable Long id, Model model) {
        Server server = serverService.dohvatiPoid(id).orElse(null);

        if (server == null) {
            return "redirect:/servers";
        }

        model.addAttribute("server", server);
        return "servers/form";
    }

    // spremi promjene
    @PostMapping("/{id}/edit")
    public String azuriraj(@PathVariable Long id,
                           @Valid @ModelAttribute Server server,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "servers/form";
        }

        server.setId(id);
        serverService.spremi(server);
        redirectAttributes.addFlashAttribute("poruka", "Server uspješno ažuriran!");
        return "redirect:/servers/" + id;
    }

    // potvrda brisanja
    @GetMapping("/{id}/delete")
    public String obrisiForma(@PathVariable Long id, Model model) {
        Server server = serverService.dohvatiPoid(id).orElse(null);

        if (server == null) {
            return "redirect:/servers";
        }

        model.addAttribute("server", server);
        model.addAttribute("brIntervencija", intervencijaService.brojZaServer(id));
        return "servers/delete";
    }

    // izvrsi brisanje
    @PostMapping("/{id}/delete")
    public String obrisi(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        serverService.obrisi(id);
        redirectAttributes.addFlashAttribute("poruka", "Server obrisan.");
        return "redirect:/servers";
    }
}
