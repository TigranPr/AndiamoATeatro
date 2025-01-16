package entity;

import java.time.LocalDateTime;

public class Spettacolo {
    private Integer id;
    private LocalDateTime dataOra;
    private String genere;
    private Double prezzo;
    private Double durataInOre;
    private Integer idSala;

    public Spettacolo() {
    }

    public Spettacolo(Integer id, LocalDateTime dataOra, String genere, Double prezzo, Double durataInOre, Integer idSala) {
        this.id = id;
        this.dataOra = dataOra;
        this.genere = genere;
        this.prezzo = prezzo;
        this.durataInOre = durataInOre;
        this.idSala = idSala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Double getDurataInOre() {
        return durataInOre;
    }

    public void setDurataInOre(Double durataInOre) {
        this.durataInOre = durataInOre;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }
}
