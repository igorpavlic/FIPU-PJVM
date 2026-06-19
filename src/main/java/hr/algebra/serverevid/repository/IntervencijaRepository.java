package hr.algebra.serverevid.repository;

import hr.algebra.serverevid.model.Intervencija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntervencijaRepository extends JpaRepository<Intervencija, Long> {

    // dohvati sve intervencije za odredeni server, sortirane po datumu
    List<Intervencija> findByServerIdOrderByDatumDesc(Long serverId);

    // broj intervencija za server
    int countByServerId(Long serverId);
}
