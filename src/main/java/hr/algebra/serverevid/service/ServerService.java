package hr.algebra.serverevid.service;

import hr.algebra.serverevid.model.Server;
import hr.algebra.serverevid.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    // dohvati sve servere sortirane po nazivu
    public List<Server> dohvatiSve() {
        return serverRepository.findAllByOrderByNazivAsc();
    }

    // pretraga servera
    public List<Server> pretrazi(String tekst) {
        if (tekst == null || tekst.isEmpty()) {
            return dohvatiSve();
        }
        return serverRepository.pretrazi(tekst);
    }

    // dohvati server po ID-u
    public Optional<Server> dohvatiPoid(Long id) {
        return serverRepository.findById(id);
    }

    // spremi ili azuriraj server
    public void spremi(Server server) {
        serverRepository.save(server);
    }

    // obrisi server
    public void obrisi(Long id) {
        serverRepository.deleteById(id);
    }

    // ukupan broj servera (za pocetnu stranicu)
    public long ukupnoBroj() {
        return serverRepository.count();
    }
}
