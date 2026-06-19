package hr.algebra.serverevid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Intervencija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Opis je obavezan")
    private String opis;

    @NotNull(message = "Datum je obavezan")
    private LocalDate datum;

    private String tehnicar;

    // PREVENTIVNO, KOREKTIVNO ili HITNO
    private String tip;

    private Integer trajanjeSati;

    // veza s serverom - vise intervencija pripada jednom serveru
    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getTehnicar() {
        return tehnicar;
    }

    public void setTehnicar(String tehnicar) {
        this.tehnicar = tehnicar;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getTrajanjeSati() {
        return trajanjeSati;
    }

    public void setTrajanjeSati(Integer trajanjeSati) {
        this.trajanjeSati = trajanjeSati;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
