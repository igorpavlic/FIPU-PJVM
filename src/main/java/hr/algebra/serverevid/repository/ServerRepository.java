package hr.algebra.serverevid.repository;

import hr.algebra.serverevid.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServerRepository extends JpaRepository<Server, Long> {

    // pretraga - trazi po nazivu, ip adresi ili OS-u
    @Query("SELECT s FROM Server s WHERE " +
           "LOWER(s.naziv) LIKE LOWER(CONCAT('%', :tekst, '%')) OR " +
           "LOWER(s.ipAdresa) LIKE LOWER(CONCAT('%', :tekst, '%')) OR " +
           "LOWER(s.operativniSustav) LIKE LOWER(CONCAT('%', :tekst, '%'))")
    List<Server> pretrazi(@Param("tekst") String tekst);

    // sortiranje po nazivu
    List<Server> findAllByOrderByNazivAsc();
}
