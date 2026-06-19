package hr.algebra.serverevid.controller;

import hr.algebra.serverevid.model.Intervencija;
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

@Controller
@RequestMapping("/servers/{serverId}/intervencije")
public class IntervencijaController {

    @Autowired
    private IntervencijaService intervencijaService;

    @Autowired
    private ServerService serverService;

    // forma za novu intervenciju
    @GetMapping("/new")
    public String novaForma(@PathVariable Long serverId, Model model) {
        Server server = serverService.dohvatiPoid(serverId).orElse(null);

        if (server == null) {
            return "redirect:/servers";
        }

        model.addAttribute("intervencija", new Intervencija());
        model.addAttribute("server", server);
        return "intervencije/form";
    }

    // spremi novu intervenciju
    @PostMapping
    public String spremi(@PathVariable Long serverId,
                         @Valid @ModelAttribute Intervencija intervencija,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        Server server = serverService.dohvatiPoid(serverId).orElse(null);

        if (result.hasErrors()) {
            model.addAttribute("server", server);
            return "intervencije/form";
        }

        intervencija.setServer(server);
        intervencijaService.spremi(intervencija);
        redirectAttributes.addFlashAttribute("poruka", "Intervencija dodana!");
        return "redirect:/servers/" + serverId;
    }

    // forma za uredivanje
    @GetMapping("/{id}/edit")
    public String urediForma(@PathVariable Long serverId,
                             @PathVariable Long id,
                             Model model) {

        Server server = serverService.dohvatiPoid(serverId).orElse(null);
        Intervencija intervencija = intervencijaService.dohvatiPoId(id).orElse(null);

        if (server == null || intervencija == null) {
            return "redirect:/servers";
        }

        model.addAttribute("intervencija", intervencija);
        model.addAttribute("server", server);
        return "intervencije/form";
    }

    // spremi promjene
    @PostMapping("/{id}/edit")
    public String azuriraj(@PathVariable Long serverId,
                           @PathVariable Long id,
                           @Valid @ModelAttribute Intervencija intervencija,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        Server server = serverService.dohvatiPoid(serverId).orElse(null);

        if (result.hasErrors()) {
            model.addAttribute("server", server);
            return "intervencije/form";
        }

        intervencija.setId(id);
        intervencija.setServer(server);
        intervencijaService.spremi(intervencija);
        redirectAttributes.addFlashAttribute("poruka", "Intervencija ažurirana!");
        return "redirect:/servers/" + serverId;
    }

    // obrisi intervenciju
    @PostMapping("/{id}/delete")
    public String obrisi(@PathVariable Long serverId,
                         @PathVariable Long id,
                         RedirectAttributes redirectAttributes) {

        intervencijaService.obrisi(id);
        redirectAttributes.addFlashAttribute("poruka", "Intervencija obrisana.");
        return "redirect:/servers/" + serverId;
    }
}
