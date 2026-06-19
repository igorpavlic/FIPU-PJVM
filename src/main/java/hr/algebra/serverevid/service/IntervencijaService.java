package hr.algebra.serverevid.service;

import hr.algebra.serverevid.model.Intervencija;
import hr.algebra.serverevid.repository.IntervencijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntervencijaService {

    @Autowired
    private IntervencijaRepository intervencijaRepository;

    // sve intervencije za jedan server
    public List<Intervencija> dohvatiZaServer(Long serverId) {
        return intervencijaRepository.findByServerIdOrderByDatumDesc(serverId);
    }

    // dohvati jednu intervenciju
    public Optional<Intervencija> dohvatiPoId(Long id) {
        return intervencijaRepository.findById(id);
    }

    // spremi intervenciju
    public void spremi(Intervencija intervencija) {
        intervencijaRepository.save(intervencija);
    }

    // obrisi
    public void obrisi(Long id) {
        intervencijaRepository.deleteById(id);
    }

    // ukupan broj intervencija
    public long ukupnoBroj() {
        return intervencijaRepository.count();
    }

    // broj intervencija za odredjeni server
    public int brojZaServer(Long serverId) {
        return intervencijaRepository.countByServerId(serverId);
    }
}
