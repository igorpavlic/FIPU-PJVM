package hr.algebra.serverevid.controller;

import hr.algebra.serverevid.model.Intervencija;
import hr.algebra.serverevid.model.Server;
import hr.algebra.serverevid.service.IntervencijaService;
import hr.algebra.serverevid.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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

    // stranica s ukupnom statistikom svih intervencija
    @GetMapping("/statistika")
    public String statistika(Model model) {
        List<Intervencija> sveIntervencije = intervencijaService.dohvatiSve();

        // datumi kao stringovi za graf po mjesecima
        List<String> datumi = new ArrayList<>();
        for (Intervencija i : sveIntervencije) {
            datumi.add(i.getDatum().toString());
        }

        // brojanje po tipu intervencije
        int brPreventivno = 0;
        int brKorektivno = 0;
        int brHitno = 0;
        int ukupnoSati = 0;
        for (Intervencija i : sveIntervencije) {
            if ("PREVENTIVNO".equals(i.getTip())) {
                brPreventivno++;
            } else if ("KOREKTIVNO".equals(i.getTip())) {
                brKorektivno++;
            } else if ("HITNO".equals(i.getTip())) {
                brHitno++;
            }
            if (i.getTrajanjeSati() != null) {
                ukupnoSati = ukupnoSati + i.getTrajanjeSati();
            }
        }

        // Top 5 servera po broju intervencija
        List<Server> sviServeri = serverService.dohvatiSve();
        List<TopServer> topServeri = new ArrayList<>();
        for (Server s : sviServeri) {
            int broj = intervencijaService.brojZaServer(s.getId());
            topServeri.add(new TopServer(s.getId(), s.getNaziv(), broj));
        }

        // sortiranje od najveceg prema najmanjem (jednostavan bubble sort)
        for (int a = 0; a < topServeri.size() - 1; a++) {
            for (int b = 0; b < topServeri.size() - 1 - a; b++) {
                if (topServeri.get(b).getBroj() < topServeri.get(b + 1).getBroj()) {
                    TopServer temp = topServeri.get(b);
                    topServeri.set(b, topServeri.get(b + 1));
                    topServeri.set(b + 1, temp);
                }
            }
        }

        // uzmi samo prvih 5
        List<TopServer> top5 = new ArrayList<>();
        for (int k = 0; k < topServeri.size() && k < 5; k++) {
            top5.add(topServeri.get(k));
        }

        model.addAttribute("brojIntervencija", sveIntervencije.size());
        model.addAttribute("datumi", datumi);
        model.addAttribute("brPreventivno", brPreventivno);
        model.addAttribute("brKorektivno", brKorektivno);
        model.addAttribute("brHitno", brHitno);
        model.addAttribute("ukupnoSati", ukupnoSati);
        model.addAttribute("top5", top5);
        return "statistika";
    }

    // pomocna klasa za Top 5 servera
    public static class TopServer {
        private Long id;
        private String naziv;
        private int broj;

        public TopServer(Long id, String naziv, int broj) {
            this.id = id;
            this.naziv = naziv;
            this.broj = broj;
        }

        public Long getId() {
            return id;
        }

        public String getNaziv() {
            return naziv;
        }

        public int getBroj() {
            return broj;
        }
    }
}
