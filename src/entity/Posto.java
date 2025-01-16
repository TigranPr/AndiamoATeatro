package entity;

public class Posto {
    private Integer id;
    private Character fila;
    private Integer numero;
    private Integer idSala;

    public Posto() {
    }

    public Posto(Integer id, Character fila, Integer numero,Integer idSala) {
        this.id = id;
        this.fila = fila;
        this.numero = numero;
        this.idSala = idSala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getFila() {
        return fila;
    }

    public void setFila(Character fila) {
        this.fila = fila;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }
}
