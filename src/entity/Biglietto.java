package entity;

public class Biglietto {
    private Integer id;
    private Integer idUtente;
    private Integer idSpettacolo;
    private Integer idPosto;

    public Biglietto() {
    }

    public Biglietto(Integer id, Integer idUtente, Integer idSpettacolo, Integer idPosto) {
        this.id = id;
        this.idUtente = idUtente;
        this.idSpettacolo = idSpettacolo;
        this.idPosto = idPosto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    public Integer getIdSpettacolo() {
        return idSpettacolo;
    }

    public void setIdSpettacolo(Integer idSpettacolo) {
        this.idSpettacolo = idSpettacolo;
    }

    public Integer getIdPosto() {
        return idPosto;
    }

    public void setIdPosto(Integer idPosto) {
        this.idPosto = idPosto;
    }
}
