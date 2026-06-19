package hr.algebra.serverevid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.ArrayList;

// entitet za server
@Entity
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Naziv je obavezan")
    private String naziv;

    @NotBlank(message = "IP adresa je obavezna")
    private String ipAdresa;

    private String operativniSustav;

    private String lokacija;

    // moze biti: AKTIVAN, NEAKTIVAN, SERVIS
    private String status;

    private String napomena;

    @OneToMany(mappedBy = "server", cascade = CascadeType.ALL)
    private List<Intervencija> intervencije = new ArrayList<>();

    // getteri i setteri

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getIpAdresa() {
        return ipAdresa;
    }

    public void setIpAdresa(String ipAdresa) {
        this.ipAdresa = ipAdresa;
    }

    public String getOperativniSustav() {
        return operativniSustav;
    }

    public void setOperativniSustav(String operativniSustav) {
        this.operativniSustav = operativniSustav;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public List<Intervencija> getIntervencije() {
        return intervencije;
    }

    public void setIntervencije(List<Intervencija> intervencije) {
        this.intervencije = intervencije;
    }
}
