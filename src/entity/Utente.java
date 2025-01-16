package entity;

public class Utente {
    private Integer id;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String telefono;

    public Utente() {
    }

    public Utente(Integer id, String nome, String cognome, String email, String indirizzo, String telefono) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
